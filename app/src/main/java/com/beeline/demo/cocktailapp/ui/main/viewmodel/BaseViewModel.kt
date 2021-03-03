package com.beeline.demo.cocktailapp.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import com.beeline.demo.cocktailapp.data.provider.Provider
import com.beeline.demo.cocktailapp.data.repositiory.Repository

open class BaseViewModel(open val provider: Provider): ViewModel()
