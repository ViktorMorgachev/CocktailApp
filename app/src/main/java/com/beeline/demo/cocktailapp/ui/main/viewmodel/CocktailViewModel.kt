package com.beeline.demo.cocktailapp.ui.main.viewmodel

import com.beeline.demo.cocktailapp.R
import com.beeline.demo.cocktailapp.data.model.Cocktails
import com.beeline.demo.cocktailapp.data.model.Drinks
import com.beeline.demo.cocktailapp.data.repositiory.CocktailsRepository
import com.beeline.demo.cocktailapp.utils.uiScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber

class CocktailViewModel(
    override val repository: CocktailsRepository
) : BaseViewModel<Cocktails>(repository) {

    fun getHistory(): MutableList<Drinks> {
        return repository.getCocktailsHistory()
    }

    override fun init() {
        GlobalScope.launch {
            try {
                view?.showLoading(true)
                val job = async { repository.getCocktail() }
                val cocktail = job.await()
                uiScope {
                    view?.showLoading(false)
                    view?.showData(cocktail)
                }
            } catch (e: Throwable) {
                Timber.e(e)
                view?.showError(R.string.error)
            }
        }
    }

}