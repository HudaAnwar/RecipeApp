package com.huda.recipeapp.repository

import com.huda.recipeapp.domain.model.Recipe
import com.huda.recipeapp.domain.util.DomainMapper
import com.huda.recipeapp.network.RecipeService
import com.huda.recipeapp.network.model.RecipeDto
import com.huda.recipeapp.network.model.RecipeDtoMapper
import javax.inject.Inject


class RecipeRepository_Impl(
    private val recipeService: RecipeService,
    private val mapper: RecipeDtoMapper
) : RecipeRepository {
    override suspend fun search(token: String, page: Int, query: String): List<Recipe> {
        val res = recipeService.search(token, page, query)
        val recipes = res.recipes
        return mapper.toDomainList(recipes)
    }

    override suspend fun get(token: String, id: Int): Recipe {
        return mapper.mapToDomainModel(recipeService.get(token, id))
    }
}