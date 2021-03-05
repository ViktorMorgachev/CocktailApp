package com.beeline.demo.cocktailapp.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import com.beeline.demo.cocktailapp.data.repositiory.Repository
import com.beeline.demo.cocktailapp.ui.main.view.View

abstract class BaseViewModel<T>(open val repository: Repository) : ViewModel() {
    var view: View<T>? = null
    open fun init() = Unit
}
