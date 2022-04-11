package com.huda.recipeapp.network.model

import com.huda.recipeapp.domain.model.Recipe
import com.huda.recipeapp.domain.util.DomainMapper

class RecipeDtoMapper : DomainMapper<RecipeDto, Recipe> {
    override fun mapToDomainModel(model: RecipeDto): Recipe {
        with(model){
            return Recipe(
                id = pk,
                title = title,
                featuredImage = featuredImage,
                rating = rating,
                publisher = publisher,
                sourceUrl = sourceUrl,
                dateAdded = dateAdded,
                description = description,
                cookingInstructions = cookingInstructions,
                ingredients = ingredients?: listOf(),
                dateUpdated = dateUpdated
            )
        }
    }

    override fun mapFromDomainModel(domainModel: Recipe): RecipeDto {
        with(domainModel){
            return RecipeDto(
                pk = id,
                title = title,
                featuredImage = featuredImage,
                rating = rating,
                publisher = publisher,
                sourceUrl = sourceUrl,
                dateAdded = dateAdded,
                description = description,
                cookingInstructions = cookingInstructions,
                ingredients = ingredients,
                dateUpdated = dateUpdated
            )
        }
    }
    fun toDomainList(initial:List<RecipeDto>):List<Recipe>{
        return initial.map { mapToDomainModel(it) }
    }
    fun fromDomainList(initial:List<Recipe>):List<RecipeDto>{
        return initial.map { mapFromDomainModel(it) }
    }
}