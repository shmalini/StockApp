package com.example.stockscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(
    private val repo: AssetRepository,
    private val favRepo: FavRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StockViewModel::class.java)) {
            return StockViewModel(repo, favRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
