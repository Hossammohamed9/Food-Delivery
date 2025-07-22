package com.example.fooddelivery.data.source.remote

import com.example.fooddelivery.core.data.remote.safeCall
import com.example.fooddelivery.core.domain.util.DataError
import com.example.fooddelivery.core.domain.util.Result
import com.example.fooddelivery.core.domain.util.map
import com.example.fooddelivery.data.mappers.order.toOrder
import com.example.fooddelivery.data.network.OrdersApiService
import com.example.fooddelivery.domain.data_source.order.OrderRemoteDataSource
import com.example.fooddelivery.domain.models.order.Order
import javax.inject.Inject

class OrderRemoteDataSourceImpl @Inject constructor(
    private val ordersApiService: OrdersApiService
) : OrderRemoteDataSource{

    override suspend fun getOrders(): Result<List<Order>, DataError> =
        safeCall { ordersApiService.getOrders() }.map { response ->
            response.map { it.toOrder() }
        }

    override suspend fun getOrderDetails(orderId: String): Result<Order, DataError> =
        safeCall {
            ordersApiService.getOrderDetails(orderId)
        }.map { response ->
            response.toOrder()
        }

}