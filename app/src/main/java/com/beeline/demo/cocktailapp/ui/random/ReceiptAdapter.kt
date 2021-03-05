package com.beeline.demo.cocktailapp.ui.random

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beeline.demo.cocktailapp.R
import kotlinx.android.synthetic.main.item_cocktail_receipt.view.*

class ReceiptAdapter(
    private val drink: List<Pair<String, String>>
) : RecyclerView.Adapter<ReceiptAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(drink: Pair<String, String>) {
            itemView.receipt_text.text = drink.first
            itemView.receipt_info.text = drink.second
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_cocktail_receipt, parent,
                false
            )
        )

    override fun getItemCount(): Int = drink.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(drink[position])

}