package com.beeline.demo.cocktailapp.ui.main.viewmodel

import com.beeline.demo.cocktailapp.data.model.Drinks
import com.beeline.demo.cocktailapp.data.repositiory.CocktailsRepository

class DrinkViewModel(override val repository: CocktailsRepository): BaseViewModel<Drinks>(repository) {
    fun getHistory() {
        view?.showData(repository.getCocktailsHistory())
    }
}