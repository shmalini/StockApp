package com.example.stockscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class StockViewModel(
    private val repository: AssetRepository,
    private val favRepo: FavRepository
) : ViewModel() {

    private val _stocks = MutableLiveData<List<Stock>>()
    private val _query = MutableLiveData("")
    val query: LiveData<String> = _query

    private val _isRefreshing = MutableLiveData(false)
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    private val _filteredStocks = MutableLiveData<List<Stock>>()
    val filteredStocks: LiveData<List<Stock>> = _filteredStocks

    private val _lastUpdated = MutableLiveData<String?>()
    val lastUpdated: LiveData<String?> = _lastUpdated

    enum class FilterType {
        ALL, FAVOURITES
    }

    private val _filterType = MutableLiveData(FilterType.ALL)
    val filterType: LiveData<FilterType> = _filterType

    init {
        loadStockData()
        val formatter = SimpleDateFormat("MMM dd, HH:mm", Locale.getDefault())
        formatter.timeZone = TimeZone.getTimeZone("Asia/Kuala_Lumpur")
        _lastUpdated.postValue("Last updated: ${formatter.format(Date())}")


    }

    private fun loadStockData() {
        val stockList = repository.loadStocks().map { stock ->
            stock.copy(isFav = favRepo.getFavs().contains(stock.symbol))
        }
        _stocks.value = stockList
        applyFilter()
    }

    fun onFilterChanged(newFilter: FilterType) {
        _filterType.value = newFilter
        applyFilter()
    }

    fun onQueryChanged(newQuery: String) {
        _query.value = newQuery
        applyFilter()
    }

    private fun applyFilter() {
        val all = _stocks.value ?: emptyList()
        val query = _query.value.orEmpty()

        val filtered = all.filter { stock ->
            val matchesFilter = when (_filterType.value) {
                FilterType.FAVOURITES -> stock.isFav
                else -> true
            }

            val matchesQuery = stock.name.contains(query, ignoreCase = true) ||
                    stock.symbol.contains(query, ignoreCase = true)

            matchesFilter && matchesQuery
        }

        _filteredStocks.value = filtered
    }

    fun refreshStocks() {
        _isRefreshing.value = true

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
        applyFilter()

        // Update last updated timestamp
        val formatter = SimpleDateFormat("MMM dd, HH:mm", Locale.getDefault())
        formatter.timeZone = TimeZone.getTimeZone("Asia/Kuala_Lumpur")
        _lastUpdated.postValue("Last updated: ${formatter.format(Date())}")

        // Simulate short delay for spinner
        Thread {
            Thread.sleep(500)
            _isRefreshing.postValue(false)
        }.start()
    }

    fun toggleFav(symbol: String) {
        favRepo.toggle(symbol)
        _stocks.value = _stocks.value?.map {
            if (it.symbol == symbol) it.copy(isFav = !it.isFav) else it
        }
        applyFilter()
    }
}
