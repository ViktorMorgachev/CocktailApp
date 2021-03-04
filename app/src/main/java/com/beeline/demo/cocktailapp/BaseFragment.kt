package com.beeline.demo.cocktailapp

import InformationDialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.beeline.demo.cocktailapp.data.provider.CocktailsProvider
import com.beeline.demo.cocktailapp.ui.main.viewmodel.BaseViewModel
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinApiExtension

abstract class BaseFragment(layoutId: Int) : Fragment(layoutId) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    protected fun showInformationDialog(message: Int) {
        InformationDialogFragment(message = message).show(childFragmentManager, "InformationDialogFragment")
    }
}