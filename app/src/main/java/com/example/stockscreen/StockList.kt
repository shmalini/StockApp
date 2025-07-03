package com.example.stockscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text

import com.example.stockscreen.StockViewModel
import com.example.stockscreen.Stock
import androidx.compose.runtime.livedata.observeAsState


import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun StockList(viewModel: StockViewModel, modifier: Modifier = Modifier) {
    val stocks by viewModel.filteredStocks.observeAsState(emptyList())
    val query by viewModel.query.observeAsState("")

    Column(modifier = modifier.padding(16.dp)) {

        // 🔍 Search bar
        androidx.compose.material3.OutlinedTextField(
            value = query,
            onValueChange = { viewModel.onQueryChanged(it) },
            label = { Text("Search stocks") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            singleLine = true
        )

        // 🔁 Refresh button
        Button(onClick = { viewModel.refreshStocks() }) {
            Text("Refresh")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 📃 Stock list
        LazyColumn {
            items(stocks) { stock: Stock ->
                StockItem(stock = stock)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}


@Composable
fun StockItem(stock: Stock) {
    val changeColor = when {
        stock.stockPrice.priceChange > 0 -> Color(0xFFB6F2C0) // green
        stock.stockPrice.priceChange < 0 -> Color(0xFFFFC0C0) // red
        else -> Color.LightGray
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(changeColor)
            .padding(16.dp)
    ) {
        Text(text = stock.name, fontWeight = FontWeight.Bold)
        Text(text = "${stock.symbol} - ${stock.stockPrice.currentPrice.amount} ${stock.stockPrice.currentPrice.currency}")
        Text(text = "Change: ${stock.stockPrice.priceChange} (${stock.stockPrice.percentageChange}%)")
    }
}
