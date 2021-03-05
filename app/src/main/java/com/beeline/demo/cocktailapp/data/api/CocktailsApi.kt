package com.beeline.demo.cocktailapp.data.api

import com.beeline.demo.cocktailapp.data.model.Cocktails
import retrofit2.http.GET

interface CocktailsApi: Api {
   @GET("random.php")
   suspend fun getRandomCocktail(): Cocktails
}