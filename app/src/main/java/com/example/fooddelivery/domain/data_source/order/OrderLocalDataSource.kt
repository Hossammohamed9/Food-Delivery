package com.example.fooddelivery.domain.data_source.order

import com.example.fooddelivery.domain.models.order.Order
import kotlinx.coroutines.flow.Flow

interface OrderLocalDataSource {
    fun getOrders(): Flow<List<Order>>
    suspend fun updateOrders(order: List<Order>)
    fun updateOrderStatus(orderId: String, orderStatus: String)
    fun getOrderDetails(orderId: String): Flow<Order>
    suspend fun updateOrderDetails(orderId: String, price: String, notes: String)
}