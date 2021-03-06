package com.beeline.demo.cocktailapp.ui.main.viewmodel

import com.beeline.demo.cocktailapp.data.model.Drinks
import com.beeline.demo.cocktailapp.data.repositiory.CocktailsRepository
import com.beeline.demo.cocktailapp.ui.random.CocktailHelper
import kotlin.properties.Delegates

class DrinkViewModel(override val repository: CocktailsRepository) :
    BaseViewModel<Drinks>(repository) {

    var drinks: MutableList<Drinks> by Delegates.observable(mutableListOf()) { prop, old, new ->
        view?.showData(new)
    }

    fun getHistory() {
        drinks = repository.getCocktailsHistory()
    }
}