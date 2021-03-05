package com.beeline.demo.cocktailapp.data.repositiory

import com.beeline.demo.cocktailapp.data.model.Cocktails
import com.beeline.demo.cocktailapp.data.network.Resource

interface CocktailRemoteRepository{
    suspend fun getCocktail(): Resource<Cocktails?>
}