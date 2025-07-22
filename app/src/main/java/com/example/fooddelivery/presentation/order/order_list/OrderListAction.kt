package com.example.fooddelivery.presentation.order.order_list

import com.example.fooddelivery.domain.models.order.Order


sealed interface OrderListAction {
    data class OnOrderClick(val order: Order): OrderListAction
}