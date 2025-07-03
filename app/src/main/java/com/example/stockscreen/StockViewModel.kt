package com.example.stockscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel

class StockViewModel(private val repository: AssetRepository) : ViewModel() {

    private val _stocks = MutableLiveData<List<Stock>>()
    private val _query = MutableLiveData("")
    val query: LiveData<String> = _query

    val filteredStocks: LiveData<List<Stock>> = MediatorLiveData<List<Stock>>().apply {
        addSource(_stocks) { value = filterStocks(_query.value, it) }
        addSource(_query) { value = filterStocks(it, _stocks.value) }
    }

    init {
        loadStockData()
    }

    private fun loadStockData() {
        val stockList = repository.loadStocks()
        _stocks.value = stockList
    }

    fun onQueryChanged(newQuery: String) {
        _query.value = newQuery
    }

    private fun filterStocks(query: String?, stocks: List<Stock>?): List<Stock> {
        if (query.isNullOrBlank()) return stocks ?: emptyList()
        return stocks?.filter {
            it.name.contains(query, ignoreCase = true)
        } ?: emptyList()
    }

    fun refreshStocks() {
        val updated = _stocks.value?.map { stock ->
            val randomChange = (-5..5).random() + (0..99).random() / 100.0
            val newPrice = stock.stockPrice.currentPrice.amount.toDouble() + randomChange
            stock.copy(
                stockPrice = stock.stockPrice.copy(
                    currentPrice = stock.stockPrice.currentPrice.copy(
                        amount = String.format("%.2f", newPrice)
                    ),
                    priceChange = String.format("%.2f", randomChange).toDouble(),
                    percentageChange = String.format("%.2f", (randomChange / newPrice * 100)).toDouble()
                )
            )
        } ?: emptyList()

        _stocks.value = updated
    }
}
