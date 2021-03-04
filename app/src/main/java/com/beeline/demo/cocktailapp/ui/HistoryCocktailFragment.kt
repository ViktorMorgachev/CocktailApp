package com.beeline.demo.cocktailapp.ui


import com.beeline.demo.cocktailapp.BaseFragment
import com.beeline.demo.cocktailapp.R
import com.beeline.demo.cocktailapp.ui.main.viewmodel.CocktailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryCocktailFragment(layoutId: Int) : BaseFragment(layoutId) {
    val viewModel: CocktailViewModel by viewModel()

    companion object{
        fun create(): HistoryCocktailFragment{
            return HistoryCocktailFragment(R.layout.fragment_layout_cocktail_history)
        }
    }
}