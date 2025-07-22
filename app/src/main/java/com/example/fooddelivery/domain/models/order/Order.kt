package com.example.fooddelivery.domain.models.order

data class Order(
    val id: String,
    val customerName: String,
    val restaurant: String,
    val status: String,
    val price: String = "N/A",
    val notes: String = "N/A"
)