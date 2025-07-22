package com.example.fooddelivery.domain.repository.order

import com.example.fooddelivery.core.domain.util.DataError
import com.example.fooddelivery.core.domain.util.Result
import com.example.fooddelivery.domain.models.order.Order
import kotlinx.coroutines.flow.Flow

interface OrdersRepository {
    fun getOrders(): Flow<List<Order>>
    suspend fun fetchLatestOrders(): Result<Boolean, DataError>
    fun updateOrderStatus(orderId: String, orderStatus: String)
    fun getOrderDetails(orderId: String): Flow<Order>
    suspend fun fetchOrderDetails(orderId: String): Result<Boolean, DataError>
}