package com.beeline.demo.cocktailapp.ui.random

import com.beeline.demo.cocktailapp.data.model.Drinks

object CocktailHelper {
    fun getReceiptInfo(drink: Drinks): List<Pair<String, String>> {
        val receipts = mutableListOf<Pair<String, String>>()
        for ((receipt, receiptInfo) in drink.getIngridients().zip(drink.getMeasures())) {
            receipts.add(receipt to receiptInfo)
        }
        return receipts
    }

    fun getDrinksInfo(drink: List<Drinks>): MutableList<Pair<String, String>> {
        val receipts = mutableListOf<Pair<String, String>>()
        for (item in drink) {
            receipts.add(item.strDrinkThumb to item.strDrink)
        }
        return receipts
    }
}