package com.beeline.demo.cocktailapp.data.repositiory

import com.beeline.demo.cocktailapp.data.api.CocktailsApi
import com.beeline.demo.cocktailapp.data.model.Cocktails
import com.beeline.demo.cocktailapp.data.model.Drinks
import com.beeline.demo.cocktailapp.data.repositiory.base.BaseRepository
import com.beeline.demo.cocktailapp.data.network.Resource

class CocktailsRepository(private val cocktailsApi: CocktailsApi) : BaseRepository(cocktailsApi),
    CocktailRemoteRepository, Repository {

    private val history: MutableList<Drinks> = mutableListOf()

    override suspend fun getCocktail(): Resource<Cocktails?> {
        return try {
            val response = cocktailsApi.getRandomCocktail()
            history.add(response.drinks[0])
            return responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    fun getCocktailsHistory(): MutableList<Drinks> {
        return history.take(10).toMutableList()
    }

    fun deleteFromHistoryDrinkByName(drinkName: String) {
        history.removeIf { drinkName == it.strDrink }
    }

}