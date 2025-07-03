package com.example.stockscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.stockscreen.ui.theme.StockScreenTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stockscreen.StockList


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            StockScreenTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding -> // ✅ This part was missing
                    val repo = AssetRepository(applicationContext)
                    val factory = ViewModelFactory(repo)
                    val viewModel: StockViewModel = viewModel(factory = factory)

                    StockList(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StockScreenTheme {
        Greeting("Android")
    }
}


