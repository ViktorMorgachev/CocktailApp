package com.beeline.demo.cocktailapp.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import com.beeline.demo.cocktailapp.data.provider.Provider
import com.beeline.demo.cocktailapp.ui.main.view.View

abstract class BaseViewModel<T>(open val provider: Provider) : ViewModel() {
    var view: View<T>? = null
    abstract fun init()
}
