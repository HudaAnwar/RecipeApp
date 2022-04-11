package com.huda.recipeapp.presentation.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.huda.recipeapp.presentation.ui.recipe_list.FoodCategory
import com.huda.recipeapp.presentation.ui.recipe_list.getAllFoodCategories
import kotlinx.coroutines.launch

@Composable
fun SearchAppBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    categoryScrollPosition: Int,
    onChangeCategoryScrollPosition: (Int) -> Unit,
    onSelectedCategoryChanged: (String) -> Unit,
    selectedCategory: FoodCategory?,
    onToggleTheme:()->Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        elevation = 8.dp
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(8.dp),
                    textStyle =MaterialTheme.typography.button,
                    value = query,
                    onValueChange = {
                        onQueryChanged(it)
                    },
                    label = {
                        Text(text = "Search")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    leadingIcon = {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
                    },
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            onExecuteSearch()
                        }
                    )
                )
                ConstraintLayout(
                    modifier = Modifier.align(CenterVertically)
                ) {
                    val menu = createRef()
                    IconButton(
                        onClick = onToggleTheme,
                        modifier = Modifier.constrainAs(menu) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }
                    ) {
                        Icon(
                            Icons.Filled.MoreVert,
                            contentDescription = "Toggle between dark and light theme"
                        )
                    }
                }
            }
            val scrollState = rememberScrollState()
            val scope = rememberCoroutineScope()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(state = scrollState)
                    .padding(start = 8.dp, bottom = 8.dp),

                ) {
                LaunchedEffect(key1 = "c1", block = {
                    scope.launch { scrollState.scrollTo(categoryScrollPosition) }
                })
                for (category in getAllFoodCategories()) {
                    FoodCategoryChip(
                        category = category.value,
                        isSelected = selectedCategory == category,
                        onSelectedCategoryChanged = {
                            onSelectedCategoryChanged(it)
                            onChangeCategoryScrollPosition(scrollState.value)
                        },
                        onExecuteSearch = { onExecuteSearch() }
                    )
                }
            }
        }
    }
}
