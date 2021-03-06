package com.beeline.demo.cocktailapp.di.modules

import com.beeline.demo.cocktailapp.data.provider.CocktailProvider
import com.beeline.demo.cocktailapp.data.repositiory.CocktailsRepository
import com.beeline.demo.cocktailapp.ui.main.viewmodel.CocktailViewModel
import com.beeline.demo.cocktailapp.ui.main.viewmodel.DrinkViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val cocktailModule = module {
    viewModel {
        CocktailViewModel(get())
    }
    viewModel { DrinkViewModel(get()) }
    single { CocktailsRepository(get()) }
    single { CocktailProvider(get()) }
}