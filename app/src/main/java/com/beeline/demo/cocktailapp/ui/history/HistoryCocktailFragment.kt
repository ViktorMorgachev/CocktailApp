package com.beeline.demo.cocktailapp.ui.history

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.beeline.demo.cocktailapp.R
import com.beeline.demo.cocktailapp.ui.base.BaseFragment
import com.beeline.demo.cocktailapp.data.model.Drinks
import com.beeline.demo.cocktailapp.data.provider.CocktailProvider
import com.beeline.demo.cocktailapp.ui.main.view.View
import com.beeline.demo.cocktailapp.ui.main.viewmodel.DrinkViewModel
import com.beeline.demo.cocktailapp.ui.random.CocktailHelper
import kotlinx.android.synthetic.main.fragment_random_cocktail.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryCocktailFragment(layoutId: Int) : BaseFragment(layoutId), View<Drinks> {
    val viewModel: DrinkViewModel by viewModel()

    private var adapter: DrinksAdapter? = null
    private val provider: CocktailProvider by inject()

    private val listener = { position: Int ->
        provider.deleteFromHistoryDrinkByName(adapter!!.drink[position].second)
        adapter?.removeItem(position = position)
        viewModel.getHistory()
    }

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
        adapter = DrinksAdapter(CocktailHelper.getDrinksInfo(data))
        recyclerView.adapter = adapter
        adapter?.attachSwipeToDelete(requireContext(), recyclerView, listener)
    }
}