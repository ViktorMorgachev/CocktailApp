package com.beeline.demo.cocktailapp.di.modules

import com.beeline.demo.cocktailapp.data.provider.CocktailsProvider
import com.beeline.demo.cocktailapp.data.repositiory.CocktailsRepository
import com.beeline.demo.cocktailapp.ui.main.viewmodel.CocktailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val cocktailModule = module {
    single {
        CocktailsProvider(get())
    }
    viewModel {
        CocktailViewModel(get(), get())
    }
    single { CocktailsRepository(get()) }
}