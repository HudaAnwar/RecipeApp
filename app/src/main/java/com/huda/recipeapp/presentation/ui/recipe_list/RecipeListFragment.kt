package com.huda.recipeapp.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.huda.recipeapp.presentation.BaseApplication
import com.huda.recipeapp.presentation.components.RecipeList
import com.huda.recipeapp.presentation.components.SearchAppBar
import com.huda.recipeapp.ui.theme.RecipeAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipeListFragment : Fragment() {
    val viewModel: RecipeListViewModel by viewModels()

    @Inject
    lateinit var application: BaseApplication
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {

                val recipes = viewModel.recipe.value
                val query = viewModel.query.value
                val selectedCategory = viewModel.selectedCategory.value
                val categoryScrollPosition = viewModel.categoryScrollPosition
                val page = viewModel.page.value
                val loading = viewModel.loading.value
                RecipeAppTheme(
                    darkTheme = application.isDark.value
                ) {
                    Column {
                        SearchAppBar(
                            query = query,
                            onQueryChanged = viewModel::onQueryChanged,
                            onExecuteSearch = viewModel::newSearch,
                            categoryScrollPosition = categoryScrollPosition,
                            onChangeCategoryScrollPosition = viewModel::onChangeCategoryScrollPosition,
                            onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                            selectedCategory = selectedCategory,
                            onToggleTheme = {
                                application.toggleLightTheme()
                            }
                        )

                        RecipeList(
                            recipes = recipes,
                            page = page,
                            onChangeScrollPosition = viewModel::onChangeScrollPosition,
                            onPageEnd = { viewModel.nextPage() },
                            loading = loading
                        )
                    }
                }
            }
        }


    }
}
