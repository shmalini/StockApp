package com.example.stockscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockList(
    viewModel: StockViewModel,
    modifier: Modifier = Modifier
) {
    val stocks by viewModel.filteredStocks.observeAsState(emptyList())
    val query by viewModel.query.observeAsState("")
    val filterType by viewModel.filterType.observeAsState(StockViewModel.FilterType.ALL)
    val isRefreshing by viewModel.isRefreshing.observeAsState(false)
    val lastUpdated by viewModel.lastUpdated.observeAsState()

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = {
                    Text("STOCKS", color = Color(0xFF00D1D1), fontWeight = FontWeight.Bold)
                },
                actions = {
                    IconButton(onClick = { /* TODO: Menu */ }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding() - 24.dp, start = 16.dp, end = 16.dp, bottom = innerPadding.calculateBottomPadding())
        ) {
            //Spacer(modifier = Modifier.height(2.dp)) // Reduced spacer

            // Last updated time
            lastUpdated?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            // Search bar with filter icon
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {
                OutlinedTextField(
                    value = query,
                    onValueChange = viewModel::onQueryChanged,
                    placeholder = { Text("Search stocks") },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    shape = MaterialTheme.shapes.medium
                )

                Spacer(modifier = Modifier.width(8.dp))

                IconButton(
                    onClick = { /* TODO: filter sheet */ },
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color(0xFF00D1D1), shape = MaterialTheme.shapes.medium)
                ) {
                    Icon(Icons.Default.FilterList, contentDescription = "Filter", tint = Color.White)
                }
            }

            // Filter buttons (All, Favourites)
            FilterRow(
                selected = filterType,
                onFilterSelected = viewModel::onFilterChanged,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // Pull to refresh + stock list
            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = viewModel::refreshStocks
            ) {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(stocks) { stock ->
                        StockItem(
                            stock = stock,
                            onToggleFav = viewModel::toggleFav,

                            elevation = 0.dp
                        )
                    }
                }
            }
        }
    }
}
