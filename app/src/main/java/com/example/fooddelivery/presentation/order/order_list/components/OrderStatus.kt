package com.example.fooddelivery.presentation.order.order_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddelivery.presentation.order.OrderStatus
import com.example.fooddelivery.ui.theme.FoodDeliveryTheme
import com.example.fooddelivery.ui.theme.greenBackground
import com.example.fooddelivery.ui.theme.orangeBackground

@Composable
fun OrderStatus(
    status: String,
    modifier: Modifier = Modifier
) {
    val contentColor = Color.White

    val backgroundColor = when(status.lowercase()) {
        OrderStatus.CANCELLED.status -> Color.Gray
        OrderStatus.PREPARING.status -> orangeBackground
        OrderStatus.OUT_FOR_DELIVERY.status -> Color.Blue
        else -> greenBackground
    }
    

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(100f))
            .background(backgroundColor)
            .padding(horizontal = 6.dp, vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = status,
            color = contentColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}

@PreviewLightDark
@Composable
private fun PriceChangePreview() {
    FoodDeliveryTheme {
        OrderStatus(
            status = "preparing"
        )
    }
}