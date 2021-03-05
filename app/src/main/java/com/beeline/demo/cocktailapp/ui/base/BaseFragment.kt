package com.beeline.demo.cocktailapp.ui.base


import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.beeline.demo.cocktailapp.R

abstract class BaseFragment(layoutId: Int) : Fragment(layoutId){

    protected fun showDialog(title: Int? = R.string.information, message: Int, @StringRes positiveButtonText: Int? = R.string.ok, @StringRes  negativeButtonText: Int? = null){
        val dialog = AlertDialog.Builder(requireContext())
            .setMessage(message)
            .setCancelable(true)
        positiveButtonText?.let {
            dialog.setPositiveButton(it) { _, _ ->

            }
        }
        negativeButtonText?.let {
            dialog.setNegativeButton(it) { _, _ ->
            }
        }
    }
}