package com.example.fooddelivery.data.network

import com.example.fooddelivery.data.network.dto.order.OrderDTO
import com.example.fooddelivery.data.network.dto.order.OrderDetailsDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface OrdersApiService {
    @GET("/orders")
    suspend fun getOrders(): List<OrderDTO>

    @GET("/orders/{id}")
    suspend fun getOrderDetails(@Path("id") orderId: String): OrderDetailsDTO
}