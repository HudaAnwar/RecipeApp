package com.huda.recipeapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.huda.recipeapp.R
import com.huda.recipeapp.domain.model.Recipe
import com.huda.recipeapp.util.loadPicture

const val DEFAULT_RECIPE_IMAGE = R.drawable.empty_plate

@Composable
fun RecipeCard(
    recipe: Recipe,
    onClick: () -> Unit,
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 6.dp,
                top = 6.dp
            )
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 8.dp
    ) {
        Column {
            recipe.featuredImage?.let { url ->
                LoadImage(url = url)
            }
            DisplayTitleAndRating(recipe)
        }
    }
}

@Composable
fun LoadImage(url: String) {
    val image = loadPicture(
        url = url,
        defaultImage = DEFAULT_RECIPE_IMAGE
    ).value
    image?.let { img ->
        Image(
            bitmap = img.asImageBitmap(),
            contentDescription = "placeholder",
            modifier = Modifier
                .fillMaxWidth()
                .height(225.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun DisplayTitleAndRating(recipe: Recipe) {
    recipe.title?.let { title ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 12.dp)
        ) {

            Text(
                text = title,
                style = MaterialTheme.typography.h3,
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .wrapContentWidth(Alignment.Start)
            )
            Text(
                text = recipe.rating.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End)
                    .align(Alignment.CenterVertically),
                style = MaterialTheme.typography.h5
            )
        }
    }

}