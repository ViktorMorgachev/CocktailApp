package com.beeline.demo.cocktailapp.ui.main.view

import androidx.annotation.StringRes
import com.beeline.demo.cocktailapp.data.network.Resource

interface View<T> {
    fun showError(@StringRes message: Int)
    fun showLoading(isLoading: Boolean)
    fun showData(resource: Resource<T?>)
}