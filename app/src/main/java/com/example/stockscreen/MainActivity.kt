package com.example.stockscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stockscreen.ui.theme.StockScreenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            StockScreenTheme(
                darkTheme = false // Forces lightmode
            ) {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    val repo = AssetRepository(applicationContext)
                    val favRepo = FavRepository(
                        applicationContext.getSharedPreferences("favs", MODE_PRIVATE)
                    )

                    // Inline ViewModel factory using Compose viewModel { ... }
                    val viewModel: StockViewModel = viewModel {
                        StockViewModel(repo, favRepo)
                    }

                    StockList(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }

        }
        }
    }


