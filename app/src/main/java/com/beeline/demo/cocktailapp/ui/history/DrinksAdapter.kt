package com.beeline.demo.cocktailapp.ui.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_cocktail_history.view.*
import kotlinx.android.synthetic.main.item_cocktail_receipt.view.receipt_info
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.ItemTouchHelper
import android.content.Context
import android.graphics.*
import com.beeline.demo.cocktailapp.R
import timber.log.Timber
import java.lang.Exception
import kotlin.math.abs

class DrinksAdapter(
    private val drink: MutableList<Pair<String, String>>
) : RecyclerView.Adapter<DrinksAdapter.DataViewHolder>() {
    private lateinit var swipeToDeleteCallback: SwipeToDeleteCallback
    private lateinit var itemTouchHelper: ItemTouchHelper
    private var deleteListener: ((String) -> Unit?)? = null

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(drink: Pair<String, String>) {
            itemView.receipt_image.loadImage(drink.first)
            itemView.receipt_info.text = drink.second
        }
    }

    fun attachSwipeToDelete(
        context: Context,
        recyclerView: RecyclerView,
        listener: ((String) -> Unit)? = null
    ) {
        deleteListener = listener
        swipeToDeleteCallback = SwipeToDeleteCallback(context)
        itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    fun removeItem(position: Int) {
        try {
            deleteListener?.invoke(drink[position].second)
            drink.removeAt(position)
            notifyItemChanged(position)
        } catch (e: Throwable){
            Timber.e(e)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_cocktail_history, parent,
                false
            )
        )

    override fun getItemCount(): Int = drink.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(drink[position])


   inner class SwipeToDeleteCallback internal constructor(val context: Context) :
        ItemTouchHelper.Callback() {

        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: ViewHolder): Int {
            return makeMovementFlags(0, ItemTouchHelper.LEFT)
        }

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: ViewHolder,
            viewHolder1: ViewHolder
        ): Boolean {
            return false
        }

        override fun onChildDraw(
            c: Canvas,
            recyclerView: RecyclerView,
            viewHolder: ViewHolder,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
        ) {
            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                val width = viewHolder.itemView.width.toFloat()
                val alpha = 1.0f - abs(dX) / width
                viewHolder.itemView.alpha = alpha
                viewHolder.itemView.translationX = dX
            } else {
                super.onChildDraw(
                    c, recyclerView, viewHolder, dX, dY,
                    actionState, isCurrentlyActive
                )
            }
        }

        override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
            removeItem(viewHolder.adapterPosition)
        }
    }


}

