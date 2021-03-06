package com.beeline.demo.cocktailapp.data.provider

import com.beeline.demo.cocktailapp.data.model.Drinks
import com.beeline.demo.cocktailapp.data.repositiory.CocktailsRepository

class CocktailProvider(private val cocktailsRepository: CocktailsRepository) {

    fun deleteFromHistoryDrinkByName(drinkName: String) {
        cocktailsRepository.deleteFromHistoryDrinkByName(drinkName)
    }

}