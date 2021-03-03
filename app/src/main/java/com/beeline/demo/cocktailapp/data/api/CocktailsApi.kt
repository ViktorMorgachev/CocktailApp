package com.beeline.demo.cocktailapp.data.api

import com.beeline.demo.cocktailapp.data.model.Cocktail
import retrofit2.http.GET

interface CocktailsApi: Api {
   @GET("/random.php")
   suspend fun getRandomCocktail(): Cocktail
}