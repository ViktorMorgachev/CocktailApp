package com.beeline.demo.cocktailapp.data.provider

import com.beeline.demo.cocktailapp.data.model.Cocktails
import com.beeline.demo.cocktailapp.data.repositiory.CocktailsRepository

class CocktailsProvider(protected val cocktailsRepository: CocktailsRepository) {

    private var cocktails: Cocktails? = null
    private val history: MutableSet<Cocktails> = mutableSetOf()

    fun getAllHistoryCocktail(): List<Cocktails> {
        return history.toList()
    }

    fun deleteCocktailFromHistory(cocktails: Cocktails) {
        history.remove(cocktails)
    }
}