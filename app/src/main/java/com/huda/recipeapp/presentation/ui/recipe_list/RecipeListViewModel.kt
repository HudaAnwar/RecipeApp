package com.huda.recipeapp.presentation.ui.recipe_list

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huda.recipeapp.domain.model.Recipe
import com.huda.recipeapp.repository.RecipeRepository
import com.huda.recipeapp.util.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

const val PAGE_SIZE = 30

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val repository: RecipeRepository,
    private @Named("auth_token") val token: String,
) : ViewModel() {
    val recipe: MutableState<List<Recipe>> = mutableStateOf(listOf())
    val query = mutableStateOf("")
    val selectedCategory: MutableState<FoodCategory?> = mutableStateOf(null)
    var categoryScrollPosition: Int = 0
    val loading = mutableStateOf(false)
    val page = mutableStateOf(1)
    private var scrollPosition = 0

    init {
        newSearch()
    }

    fun newSearch() {
        viewModelScope.launch {
            loading.value = true
            resetSearchState()
            delay(0)
            val result = repository.search(
                token = token,
                page = 1,
                query = query.value
            )
            recipe.value = result
            loading.value = false
        }
    }

    fun nextPage() {
        viewModelScope.launch {
            if ((scrollPosition + 1) >= (page.value * PAGE_SIZE)) {
                //loading.value = true
                incrementPage()
                Log.d(TAG, "nextPage: ${page.value}")
                delay(1000)
                if (page.value > 1) {
                    val result = repository
                        .search(token,page.value, query.value)
                    Log.d(TAG, "nextPage: $result")
                    appendAuthors(result)
                }
                //loading.value = false
            }
        }
    }

    private fun appendAuthors(authors: List<Recipe>) {
        val current = ArrayList(this.recipe.value)
        current.addAll(authors)
        this.recipe.value = current
    }

    private fun incrementPage() {
        page.value = page.value + 1
    }

    private fun resetSearchState() {
        recipe.value = listOf()
        page.value = 1
        onChangeScrollPosition(0)
        if (selectedCategory.value?.value != query.value) {
            clearSelectedCategory()
        }
    }

    private fun clearSelectedCategory() {
        selectedCategory.value = null
    }

    fun onQueryChanged(query: String) {
        this.query.value = query
    }

    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getFoodCategory(category)
        selectedCategory.value = newCategory
        onQueryChanged(category)
    }

    fun onChangeCategoryScrollPosition(position: Int) {
        categoryScrollPosition = position
    }

    fun onChangeScrollPosition(position: Int) {
        scrollPosition = position
    }
}