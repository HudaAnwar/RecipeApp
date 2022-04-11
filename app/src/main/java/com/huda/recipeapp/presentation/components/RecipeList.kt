package com.huda.recipeapp.presentation.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.huda.recipeapp.domain.model.Recipe
import com.huda.recipeapp.presentation.ui.recipe_list.PAGE_SIZE
import com.huda.recipeapp.presentation.ui.recipe_list.RecipeListViewModel
import com.huda.recipeapp.ui.theme.RecipeAppTheme
import com.huda.recipeapp.util.TAG


@Composable
fun RecipeList(
    recipes: List<Recipe>,
    page: Int,
    onChangeScrollPosition: (Int) -> Unit,
    onPageEnd: () -> Unit,
    loading: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
    ) {
        if (loading && recipes.isEmpty()) {
            LoadingRecipeListShimmer(imageHeight = 250.dp)
        } else {
            LazyColumn {
                itemsIndexed(items = recipes) { index, recipe ->
                    onChangeScrollPosition(index)
                    if ((index + 1) >= (page * PAGE_SIZE) && !loading) {
                        onPageEnd()
                    }
                    RecipeCard(
                        recipe = recipe,
                        onClick = { Log.d(TAG, "onCreateView: ") }
                    )
                }
            }
        }
        CircularIndeterminateProgressBar(isDisplayed = loading)
    }
}