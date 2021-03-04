package com.beeline.demo.cocktailapp.ui.main.view

import androidx.annotation.StringRes

interface View<T> {
    fun showError(@StringRes message: Int)
    fun showLoading(isLoading: Boolean)
    fun showData(data: T)
}