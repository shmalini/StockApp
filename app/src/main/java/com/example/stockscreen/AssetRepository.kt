package com.example.stockscreen

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AssetRepository(private val context: Context) {

    fun loadStocks(): List<Stock> {
        return try {
            val json = context.assets.open("stocks.json")
                .bufferedReader().use { it.readText() }

            val type = object : TypeToken<StockResponse>() {}.type
            val response: StockResponse = Gson().fromJson(json, type)
            response.stocks
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

}