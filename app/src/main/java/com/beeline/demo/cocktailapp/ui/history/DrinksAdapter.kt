package com.beeline.demo.cocktailapp.ui.history

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
import androidx.core.content.ContextCompat
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import com.beeline.demo.cocktailapp.R

class DrinksAdapter(
    private val drink: MutableList<Pair<String, String>>
) : RecyclerView.Adapter<DrinksAdapter.DataViewHolder>() {
    private lateinit var swipeToDeleteCallback: SwipeToDeleteCallback
    private lateinit var itemTouchHelper: ItemTouchHelper

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(drink: Pair<String, String>) {
            itemView.receipt_image.loadImage(drink.first)
            itemView.receipt_info.text = drink.second
        }
    }

    fun attachSwipeToRefresh(context: Context, recyclerView: RecyclerView) {
        swipeToDeleteCallback = SwipeToDeleteCallback(context)
        itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    fun removeItem(position: Int) {
        drink.removeAt(position)
        notifyItemRemoved(position)
    }

    fun addData(drink: MutableList<Pair<String, String>>) {
        this.drink.clear()
        this.drink.addAll(drink)
    }

    fun restoreItem(item: Pair<String, String>, position: Int) {
        drink.add(position, item)
        notifyItemInserted(position)
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
        private val mClearPaint: Paint = Paint()
        private val mBackground: ColorDrawable = ColorDrawable()
        private val backgroundColor: Int = Color.parseColor("#b80f0a")
        private val deleteDrawable: Drawable?
        private val intrinsicWidth: Int
        private val intrinsicHeight: Int
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
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            val itemView = viewHolder.itemView
            val itemHeight = itemView.height
            val isCancelled = dX == 0f && !isCurrentlyActive
            if (isCancelled) {
                clearCanvas(
                    c, itemView.right + dX,
                    itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat()
                )
                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
                return
            }
            mBackground.color = backgroundColor
            mBackground.setBounds(
                itemView.right + dX.toInt(),
                itemView.top,
                itemView.right,
                itemView.bottom
            )
            mBackground.draw(c)
            val deleteIconTop = itemView.top + (itemHeight - intrinsicHeight) / 2
            val deleteIconMargin = (itemHeight - intrinsicHeight) / 2
            val deleteIconLeft = itemView.right - deleteIconMargin - intrinsicWidth
            val deleteIconRight = itemView.right - deleteIconMargin
            val deleteIconBottom = deleteIconTop + intrinsicHeight
            deleteDrawable!!.setBounds(
                deleteIconLeft,
                deleteIconTop,
                deleteIconRight,
                deleteIconBottom
            )
            deleteDrawable.draw(c)
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }

        private fun clearCanvas(c: Canvas, left: Float, top: Float, right: Float, bottom: Float) {
            c.drawRect(left, top, right, bottom, mClearPaint)
        }

        override fun getSwipeThreshold(viewHolder: ViewHolder): Float {
            return 0.7f
        }

        override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
            removeItem(viewHolder.adapterPosition - 1)
        }

        init {
            mClearPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
            deleteDrawable = ContextCompat.getDrawable(context, R.drawable.ic_delete_24)
            intrinsicWidth = deleteDrawable!!.intrinsicWidth
            intrinsicHeight = deleteDrawable.intrinsicHeight
        }
    }


}

