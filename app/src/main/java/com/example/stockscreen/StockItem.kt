package com.example.stockscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.ui.unit.Dp


@Composable
fun StockItem(
    stock: Stock,
    onToggleFav: (String) -> Unit,
    elevation: Dp = 2.dp
) {
    val changeColor = when {
        stock.stockPrice.priceChange > 0 -> Color(0xFF4CAF50)
        stock.stockPrice.priceChange < 0 -> Color(0xFFF44336)
        else -> Color.Gray
    }

    val backgroundTint = when {
        stock.stockPrice.priceChange > 0 -> Color(0xFFEDF7ED) // light green
        stock.stockPrice.priceChange < 0 -> Color(0xFFFFEBEE) // light red
        else -> Color(0xFFF4F4F4) // soft neutral
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
        colors = CardDefaults.cardColors(containerColor = backgroundTint)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { onToggleFav(stock.symbol) },
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(24.dp)
            ) {
                Icon(
                    imageVector = if (stock.isFav) Icons.Filled.Star else Icons.Outlined.StarBorder,
                    contentDescription = "Toggle Favourite",
                    tint = if (stock.isFav) Color(0xFFFFD700) else Color.Gray
                )
            }

            AsyncImage(
                model = stock.logoUrl,
                contentDescription = "${stock.name} logo",
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stock.symbol,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black // ✅ Force black text
                )
                Text(
                    text = stock.name,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.DarkGray // ✅ Slightly lighter
                )
            }

            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "${stock.stockPrice.currentPrice.amount} ${stock.stockPrice.currentPrice.currency}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
                Text(
                    text = "${stock.stockPrice.percentageChange}%",
                    color = changeColor,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

