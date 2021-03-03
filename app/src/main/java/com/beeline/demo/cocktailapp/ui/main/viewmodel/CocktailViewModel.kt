package com.beeline.demo.cocktailapp.ui.main.viewmodel

import com.beeline.demo.cocktailapp.data.provider.CocktailsProvider
import com.beeline.demo.cocktailapp.data.repositiory.Repository

class CocktailViewModel(override val provider: CocktailsProvider) : BaseViewModel(provider = provider){
    suspend fun getCocktail() = provider.getAllRandomCocktail()
}