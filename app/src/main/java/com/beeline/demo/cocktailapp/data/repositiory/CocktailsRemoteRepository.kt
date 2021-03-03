package com.beeline.demo.cocktailapp.data.repositiory

import com.beeline.demo.cocktailapp.data.model.Cocktail

interface CocktailsRemoteRepository: Repository {
    suspend fun getRandomCocktail(): Cocktail?
}