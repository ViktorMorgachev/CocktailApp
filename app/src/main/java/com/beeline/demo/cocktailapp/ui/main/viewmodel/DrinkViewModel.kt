package com.beeline.demo.cocktailapp.ui.main.viewmodel

import com.beeline.demo.cocktailapp.data.model.Drinks
import com.beeline.demo.cocktailapp.data.repositiory.CocktailsRepository
import com.beeline.demo.cocktailapp.ui.random.CocktailHelper
import kotlin.properties.Delegates

class DrinkViewModel(override val repository: CocktailsRepository) :
    BaseViewModel<MutableList<Pair<String, String>>>(repository) {

    var drinks: List<Drinks> by Delegates.observable(mutableListOf()) { prop, old, new ->
        view?.showData(CocktailHelper.getDrinksInfo(drinks))
    }

    fun getHistory() {
        drinks = repository.getCocktailsHistory()
    }
}