package com.beeline.demo.cocktailapp.ui.random

import android.os.Bundle
import android.text.Html
import androidx.recyclerview.widget.LinearLayoutManager
import com.beeline.demo.cocktailapp.ui.base.BaseFragment
import com.beeline.demo.cocktailapp.R
import com.beeline.demo.cocktailapp.data.model.Cocktails
import com.beeline.demo.cocktailapp.data.network.Resource
import com.beeline.demo.cocktailapp.data.network.Status
import com.beeline.demo.cocktailapp.ui.main.view.View
import com.beeline.demo.cocktailapp.ui.main.viewmodel.CocktailViewModel
import gone
import kotlinx.android.synthetic.main.fragment_random_cocktail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import visible

class RandomCocktailFragment(layoutId: Int) : BaseFragment(layoutId), View<Cocktails> {
    private val viewModel: CocktailViewModel by viewModel()

    companion object {
        fun create(): RandomCocktailFragment {
            return RandomCocktailFragment(R.layout.fragment_random_cocktail)
        }
    }

    private lateinit var adapter: ReceiptAdapter

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipe_container.setOnRefreshListener {
            favourite.gone()
            viewModel.init()
        }
        viewModel.view = this
        viewModel.init()
        favourite.setOnClickListener {
            favourite.setImageResource(R.drawable.ic_favorite_red)
        }
    }

    override fun showError(message: Int) {
        showDialog(
            message = message,
            negativeButtonListener = Pair({ viewModel.init() }, R.string.ok)
        )
    }

    override fun showLoading(isLoading: Boolean) {
        requireActivity().runOnUiThread {
            swipe_container.isRefreshing = isLoading
        }
    }


    override fun showData(resource: Resource<Cocktails?>) {
        when (resource.status) {
            Status.ERROR -> {
                top_cocktail.isLoading(false)
                showError(R.string.error)
                Timber.e(resource.message)
            }
            Status.SUCCESS -> {
                resource.data?.let { showData(it) }
            }
            else -> {
            }
        }
    }

    override fun showData(data: Cocktails) {
        val cocktail = data.drinks[0]
        top_cocktail.loadImage(cocktail.strDrinkThumb)
        instructions.text = Html.fromHtml(
            resources.getString(
                R.string.ingridients,
                cocktail.strInstructions,
                cocktail.strGlass,
                cocktail.idDrink.toString(),
                cocktail.strCategory,
                cocktail.strAlcoholic
            )
        )
        top_cocktail_text.text = cocktail.strDrink
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = ReceiptAdapter(CocktailHelper.getReceiptInfo(cocktail))
        recyclerView.adapter = adapter
        favourite.setImageResource(R.drawable.ic_favorite_white)
        favourite.visible()
    }

}