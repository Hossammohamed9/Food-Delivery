package com.example.fooddelivery.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val customerName: String,
    val restaurant: String,
    val status: String,
    val price: String = "N/A",
    val notes: String = "N/A"
)
