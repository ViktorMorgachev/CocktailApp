package com.beeline.demo.cocktailapp

import androidx.fragment.app.Fragment
import com.beeline.demo.cocktailapp.data.provider.CocktailsProvider
import org.koin.android.ext.android.inject

class BaseFragment : Fragment() {
    val cocktailPresenter: CocktailsProvider by inject()
}