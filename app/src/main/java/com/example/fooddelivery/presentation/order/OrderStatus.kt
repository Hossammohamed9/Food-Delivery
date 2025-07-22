package com.example.fooddelivery.presentation.order

enum class OrderStatus(val status: String) {
    PREPARING("preparing"),
    OUT_FOR_DELIVERY("out for delivery"),
    DELIVERED("delivered"),
    CANCELLED("cancelled")
}