package com.beeline.demo.cocktailapp.ui.base


import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.beeline.demo.cocktailapp.R

abstract class BaseFragment(layoutId: Int) : Fragment(layoutId) {

    protected fun showDialog(
        title: Int = R.string.information,
        message: Int,
        positiveButtonListener: Pair<(() -> Unit)?, Int>? = null,
        negativeButtonListener: Pair<(() -> Unit)?, Int>? = null,
    ) {
        val dialog = AlertDialog.Builder(requireContext())
            .setMessage(message)
            .setTitle(title)
            .setCancelable(true)
        positiveButtonListener?.let {
            dialog.setPositiveButton(it.second) { _, _ ->
                positiveButtonListener.first?.invoke()
            }
        }
        negativeButtonListener?.let {
            dialog.setNegativeButton(it.second) { _, _ ->
                negativeButtonListener.first?.invoke()
            }
        }
        dialog.show()
    }
}