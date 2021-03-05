package com.beeline.demo.cocktailapp.ui.main.viewmodel

import com.beeline.demo.cocktailapp.R
import com.beeline.demo.cocktailapp.data.model.Cocktails
import com.beeline.demo.cocktailapp.data.provider.CocktailsProvider
import com.beeline.demo.cocktailapp.data.repositiory.CocktailsRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

class CocktailViewModel(
    override val repository: CocktailsRepository,
    val provider: CocktailsProvider
) : BaseViewModel<Cocktails>(repository) {

    override fun init() {
        GlobalScope.launch {
            try {
                view?.showLoading(true)
                val cocktail = repository.getCocktail()
                view?.showLoading(false)
                view?.showData(cocktail)
            } catch (e: Throwable) {
                Timber.e(e)
                view?.showError(R.string.error)
            }
        }
    }

}