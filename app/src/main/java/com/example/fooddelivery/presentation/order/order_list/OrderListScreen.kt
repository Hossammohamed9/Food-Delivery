package com.example.fooddelivery.presentation.order.order_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.fooddelivery.presentation.order.order_list.components.OrderListItem
import com.example.fooddelivery.presentation.order.order_list.components.previewOrder
import com.example.fooddelivery.ui.theme.FoodDeliveryTheme

@Composable
fun OrderListScreen(
    state: OrderListState,
    onAction: (OrderListAction) -> Unit,
    modifier: Modifier = Modifier
) {
    if(state.isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = state.orders) { order ->
                OrderListItem(
                    order = order,
                    onClick = {
                        onAction(OrderListAction.OnOrderClick(order))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                HorizontalDivider()
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun CoinListScreenPreview() {
    FoodDeliveryTheme {
        OrderListScreen(
            state = OrderListState(
                orders = (1..100).map {
                    previewOrder.copy(id = it.toString())
                }
            ),
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background),
            onAction = {}
        )
    }
}