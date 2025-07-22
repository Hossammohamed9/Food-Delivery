package com.example.fooddelivery.domain.data_source.order

import com.example.fooddelivery.core.domain.util.DataError
import com.example.fooddelivery.core.domain.util.Result
import com.example.fooddelivery.domain.models.order.Order


interface OrderRemoteDataSource {
    suspend fun getOrders(): Result<List<Order>, DataError>
    suspend fun getOrderDetails(orderId: String): Result<Order, DataError>
}