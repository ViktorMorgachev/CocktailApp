package com.beeline.demo.cocktailapp.data.provider

import com.beeline.demo.cocktailapp.data.model.Cocktail
import com.beeline.demo.cocktailapp.data.repositiory.CocktailsRemoteRepository

class CocktailsProvider(protected val cocktailsRemoteRepository: CocktailsRemoteRepository) :
    Provider {

    private var cocktail: Cocktail? = null
    private val history: MutableSet<Cocktail> = mutableSetOf()

    suspend fun getAllRandomCocktail(): Cocktail? {
        cocktail.let {
            cocktail = cocktailsRemoteRepository.getRandomCocktail()
        }
        cocktail?.let { history.add(it) }
        return cocktail
    }

    fun getAllHistoryCocktail(): List<Cocktail> {
        return history.toList()
    }

    fun deleteCocktailFromHistory(cocktail: Cocktail) {
        history.remove(cocktail)
    }
}