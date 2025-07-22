package com.example.fooddelivery.data.network.dto.order

data class OrderDetailsDTO(
    val id: String,
    val customerName: String,
    val restaurant: String,
    val status: String,
    val price: String,
    val notes: String
)
