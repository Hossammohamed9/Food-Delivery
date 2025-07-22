package com.example.fooddelivery.data.network.dto.order

data class OrderDTO(
    val id: String,
    val customerName: String,
    val restaurant: String,
    val status: String
)
