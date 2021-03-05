package com.beeline.demo.cocktailapp.data.repositiory

import com.beeline.demo.cocktailapp.data.api.CocktailsApi
import com.beeline.demo.cocktailapp.data.model.Cocktails
import com.beeline.demo.cocktailapp.data.repositiory.base.BaseRepository
import com.beeline.demo.cocktailapp.data.network.Resource

class CocktailsRepository(private val cocktailsApi: CocktailsApi) : BaseRepository(cocktailsApi),
    CocktailRemoteRepository, Repository {

    private var cocktails: Cocktails? = null
    private val history: MutableSet<Cocktails> = mutableSetOf()

    override suspend fun getCocktail(): Resource<Cocktails?> {
        return try {
            val response = cocktailsApi.getRandomCocktail()
            history.add(response)
            return responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    fun getCocktailsHistory(): MutableSet<Cocktails> {
        return history
    }

}