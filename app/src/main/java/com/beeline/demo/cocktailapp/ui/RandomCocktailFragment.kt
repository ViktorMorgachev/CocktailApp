package com.beeline.demo.cocktailapp.ui

import android.os.Bundle
import com.beeline.demo.cocktailapp.BaseFragment
import com.beeline.demo.cocktailapp.R
import com.beeline.demo.cocktailapp.data.model.Cocktail
import com.beeline.demo.cocktailapp.ui.main.view.View
import com.beeline.demo.cocktailapp.ui.main.viewmodel.CocktailViewModel
import kotlinx.android.synthetic.main.fragment_random_cocktail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RandomCocktailFragment(layoutId: Int) : BaseFragment(layoutId), View<Cocktail> {
    private val viewModel: CocktailViewModel by viewModel()

    companion object {
        fun create(): RandomCocktailFragment {
            return RandomCocktailFragment(R.layout.fragment_random_cocktail)
        }
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipe_container.setOnRefreshListener {
            viewModel.init()
        }
        viewModel.view = this
        viewModel.init()
    }

    override fun showError(message: Int) {
        showInformationDialog(message = message)
    }

    override fun showLoading(isLoading: Boolean) {
        swipe_container.isRefreshing = isLoading
    }

    override fun showData(data: Cocktail) {

    }
}