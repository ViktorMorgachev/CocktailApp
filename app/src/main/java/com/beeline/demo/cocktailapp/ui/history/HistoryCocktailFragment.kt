package com.beeline.demo.cocktailapp.ui.history

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.beeline.demo.cocktailapp.R
import com.beeline.demo.cocktailapp.ui.base.BaseFragment
import com.beeline.demo.cocktailapp.data.model.Cocktails
import com.beeline.demo.cocktailapp.data.model.Drinks
import com.beeline.demo.cocktailapp.ui.main.view.View
import com.beeline.demo.cocktailapp.ui.main.viewmodel.CocktailViewModel
import com.beeline.demo.cocktailapp.ui.main.viewmodel.DrinkViewModel
import com.beeline.demo.cocktailapp.ui.random.CocktailHelper
import com.beeline.demo.cocktailapp.ui.random.RandomCocktailFragment
import kotlinx.android.synthetic.main.fragment_random_cocktail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryCocktailFragment(layoutId: Int) : BaseFragment(layoutId), View<Drinks> {
    val viewModel: DrinkViewModel by viewModel()

    private var adapter: DrinksAdapter? = null

    companion object {
        fun create(): HistoryCocktailFragment {
            return HistoryCocktailFragment(R.layout.fragment_layout_cocktail_history)
        }
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.view = this
    }

    override fun onResume() {
        super.onResume()
        viewModel.getHistory()
    }

    override fun showError(message: Int) {
        showDialog(message = message)
    }

    override fun showData(data: List<Drinks>) {
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter.let {
            adapter = DrinksAdapter(mutableListOf())
            recyclerView.adapter = adapter
            adapter?.attachSwipeToRefresh(requireContext(), recyclerView)
        }
        adapter?.addData(CocktailHelper.getDrinksInfo(data))
    }
}