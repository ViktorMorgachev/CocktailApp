package com.beeline.demo.cocktailapp.data.provider

import com.beeline.demo.cocktailapp.data.model.Cocktail
import com.beeline.demo.cocktailapp.data.repositiory.CocktailsRemoteRepository

class CocktailsProvider(protected val cocktailsRemoteRepository: CocktailsRemoteRepository): Provider {

    private var cocktail: Cocktail? = null

    suspend fun getAllRandomCocktail(): Cocktail? {
        cocktail.let {
            cocktail = cocktailsRemoteRepository.getRandomCocktail()
        }
        return cocktail
    }
}