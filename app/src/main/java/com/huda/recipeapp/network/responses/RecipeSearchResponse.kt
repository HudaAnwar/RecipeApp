package com.huda.recipeapp.network.responses

import com.google.gson.annotations.SerializedName
import com.huda.recipeapp.network.model.RecipeDto

data class RecipeSearchResponse(
    @SerializedName("count")
    var count: Int,
    @SerializedName("results")
    var recipes: List<RecipeDto>
)