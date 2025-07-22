package com.example.fooddelivery.domain.models.order

data class OrderUpdate(
    val orderId: String,
    val status: String
)