package com.example.fooddelivery.presentation.order.order_list

import com.example.fooddelivery.core.domain.util.DataError


sealed interface OrderListEvent {
    data class Error(val error: DataError): OrderListEvent
}