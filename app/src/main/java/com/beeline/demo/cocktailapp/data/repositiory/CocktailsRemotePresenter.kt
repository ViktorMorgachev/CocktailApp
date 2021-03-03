package com.beeline.demo.cocktailapp.data.repositiory

import com.beeline.demo.cocktailapp.data.model.Cocktail

class CocktailsRemotePresenter: CocktailsRemoteRepository {
    override suspend fun getRandomCocktail(): Cocktail? {
        return null
    }
}