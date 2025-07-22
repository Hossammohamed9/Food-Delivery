package com.example.fooddelivery.presentation.order.order_details

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddelivery.R
import com.example.fooddelivery.presentation.order.OrderStatus
import com.example.fooddelivery.presentation.order.order_list.OrderListState
import com.example.fooddelivery.presentation.order.order_list.components.previewOrder
import com.example.fooddelivery.ui.theme.FoodDeliveryTheme
import com.example.fooddelivery.ui.theme.greenBackground
import com.example.fooddelivery.ui.theme.orangeBackground

@Composable
fun OrderDetailScreen(
    state: OrderListState,
    modifier: Modifier = Modifier
) {
    val contentColor = if(isSystemInDarkTheme()) {
        Color.White
    } else {
        Color.Black
    }
    if(state.isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if(state.selectedOrder != null) {
        val order = state.selectedOrder
        val status = state.selectedOrder?.status
        val statusImage = when(status?.lowercase()) {
            OrderStatus.CANCELLED.status -> R.drawable.cancelled
            OrderStatus.PREPARING.status -> R.drawable.preparing
            OrderStatus.OUT_FOR_DELIVERY.status -> R.drawable.out_for_delivery
            else -> R.drawable.delivered
        }

        val backgroundColor = when(status?.lowercase()) {
            OrderStatus.CANCELLED.status -> Color.Gray
            OrderStatus.PREPARING.status -> orangeBackground
            OrderStatus.OUT_FOR_DELIVERY.status -> Color.Blue
            else -> greenBackground
        }

        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Box(
                modifier = modifier
                    .clip(CircleShape)
                    .background(backgroundColor)
                    .padding(6.dp)
            ){
                Icon(
                    imageVector = ImageVector.vectorResource(
                        id = statusImage
                    ),
                    contentDescription = order?.status,
                    modifier = Modifier.size(100.dp),
                    tint = Color.White
                )
            }
            Text(
                text = order?.customerName.toString(),
                fontSize = 40.sp,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center,
                color = contentColor
            )
            DetailsRow(
                label = "Restaurant:",
                value = order?.restaurant.toString(),
                contentColor = contentColor
            )
            DetailsRow(
                label = "Price:",
                value = order?.price.toString(),
                contentColor = contentColor
            )
            DetailsRow(
                label = "Notes:",
                value = order?.notes.toString(),
                contentColor = contentColor
            )
        }
    }
}

@Composable
fun DetailsRow(label: String, value: String, contentColor: Color, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = label,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            color = contentColor,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = value,
            fontSize = 20.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.End,
            color = contentColor,
            modifier = Modifier.weight(1f)
        )
    }
}

@PreviewLightDark
@Composable
private fun CoinDetailScreenPreview() {
    FoodDeliveryTheme {
        OrderDetailScreen(
            state = OrderListState(
                orders = listOf(previewOrder),
                selectedOrderId = previewOrder.id
            ),
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}