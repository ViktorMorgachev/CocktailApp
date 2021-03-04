package com.beeline.demo.cocktailapp.ui.base

import android.content.Context
import android.content.DialogInterface
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

fun show(
    context: Context,
    message: String,
    tittle: String,
    contextLifecycle: LifecycleOwner,
    action: IAction?
) {
    show(context, message, contextLifecycle, action)
}

fun show(
    context: Context,
    message: String,
    contextLifecycle: LifecycleOwner,
    action: IAction?,
    isShowCancel: Boolean = true,
    tittle: String = "",
    cancelCaption: String = "Cancel",
    okCaption: String = "ОК",
    customView: View? = null,
    isCancelable: Boolean = true,
) {
    val alertDialog = AlertDialog.Builder(context).apply {
        setMessage(message)
        setTitle(tittle)
        setCancelable(isCancelable)
        if (isShowCancel)
            setNegativeButton(
                cancelCaption
            ) { d: DialogInterface, _: Int ->
                d.dismiss()
            }
        setPositiveButton(okCaption) { d: DialogInterface, _: Int ->
            d.dismiss()
            action?.action()
        }
        if (customView != null) {
            setView(customView)
        }
    }
    if (contextLifecycle.lifecycle.currentState == Lifecycle.State.RESUMED) {
        alertDialog.show()
    }
}
