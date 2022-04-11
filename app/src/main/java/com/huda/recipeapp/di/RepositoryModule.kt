package com.huda.recipeapp.di

import com.huda.recipeapp.network.RecipeService
import com.huda.recipeapp.network.model.RecipeDtoMapper
import com.huda.recipeapp.repository.RecipeRepository
import com.huda.recipeapp.repository.RecipeRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeService: RecipeService,
        recipeDtoMapper: RecipeDtoMapper
    ): RecipeRepository {
        return RecipeRepository_Impl(
            recipeService,
            recipeDtoMapper
        )
    }
}