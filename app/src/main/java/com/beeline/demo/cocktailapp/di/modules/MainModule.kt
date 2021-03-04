package com.beeline.demo.cocktailapp.di.modules

import com.beeline.demo.cocktailapp.data.provider.CocktailsProvider
import com.beeline.demo.cocktailapp.data.repositiory.CocktailsRemotePresenter
import com.beeline.demo.cocktailapp.data.repositiory.CocktailsRemoteRepository
import com.beeline.demo.cocktailapp.ui.main.viewmodel.CocktailViewModel
import com.beeline.demo.cocktailapp.utils.NetworkService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun mainModules() = module {
    single {
        CocktailsProvider(get())
    }
    viewModel {
        CocktailViewModel(get())
    }
    single {
        NetworkService()
    }
    single<CocktailsRemoteRepository> { CocktailsRemotePresenter() }
}