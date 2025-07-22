package com.example.fooddelivery.presentation.order.order_list

import androidx.compose.runtime.Immutable
import com.example.fooddelivery.domain.models.order.Order

@Immutable
data class OrderListState(
    val isLoading: Boolean = false,
    val orders: List<Order> = emptyList(),
    val selectedOrderId: String? = null
){
    val selectedOrder: Order?
        get() = orders.find { it.id == selectedOrderId }
}
