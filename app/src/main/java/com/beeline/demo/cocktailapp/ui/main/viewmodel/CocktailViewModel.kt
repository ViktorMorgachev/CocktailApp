package com.beeline.demo.cocktailapp.ui.main.viewmodel

import com.beeline.demo.cocktailapp.R
import com.beeline.demo.cocktailapp.data.model.Cocktail
import com.beeline.demo.cocktailapp.data.provider.CocktailsProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

class CocktailViewModel(
    override val provider: CocktailsProvider
) : BaseViewModel<Cocktail>(provider = provider) {

    override fun init() {
        GlobalScope.launch {
            try {
                view?.showLoading(true)
                val cocktail = provider.getAllRandomCocktail()
                view?.showLoading(false)
                cocktail?.let {
                    view?.showData(it)
                }
            } catch (e: Throwable) {
                Timber.e(e)
                view?.showError(R.string.error)
            }
        }
    }

}