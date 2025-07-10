package com.example.stockscreen

import com.google.gson.annotations.SerializedName

data class Stock(
    val id: Int,
    val symbol: String,
    val name: String,
    @SerializedName("logo_url") val logoUrl: String,
    @SerializedName("stock_price") val stockPrice: StockPrice,
    val isFav: Boolean = false
)

data class StockPrice(
    @SerializedName("current_price") val currentPrice: Price,
    @SerializedName("price_change") val priceChange: Double,
    @SerializedName("percentage_change") val percentageChange: Double
)

data class Price(
    val amount: String,
    val currency: String
)
