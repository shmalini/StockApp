package com.example.stockscreen

import com.google.gson.annotations.SerializedName

data class Stock(
    val symbol: String,
    val name: String,

    @SerializedName("logo_url")
    val logoUrl: String,

    @SerializedName("stock_price")
    val stockPrice: StockPrice,

    var isFav: Boolean = false
)

data class StockPrice(
    @SerializedName("current_price")
    val currentPrice: PriceAmount,

    @SerializedName("price_change")
    val priceChange: Double,

    @SerializedName("percentage_change")
    val percentageChange: Double
)

data class PriceAmount(
    val amount: String,
    val currency: String
)