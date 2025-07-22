package com.example.fooddelivery.data.repository.order

import com.example.fooddelivery.core.domain.util.DataError
import com.example.fooddelivery.core.domain.util.Result
import com.example.fooddelivery.domain.data_source.order.OrderLocalDataSource
import com.example.fooddelivery.domain.data_source.order.OrderRemoteDataSource
import com.example.fooddelivery.domain.models.order.Order
import com.example.fooddelivery.domain.repository.order.OrdersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OrdersRepositoryImpl @Inject constructor(
    private val orderRemoteDataSource: OrderRemoteDataSource,
    private val orderLocalDataSource: OrderLocalDataSource
) : OrdersRepository {

    override fun getOrders(): Flow<List<Order>> =
        orderLocalDataSource.getOrders()

    override suspend fun fetchLatestOrders(): Result<Boolean, DataError> =
        when(val remoteResult = orderRemoteDataSource.getOrders()){
            is Result.Success -> {
                orderLocalDataSource.updateOrders(remoteResult.data)
                Result.Success(true)
            }
            is Result.Error -> {
                Result.Error(remoteResult.error)
            }
        }

    override fun updateOrderStatus(orderId: String, orderStatus: String) =
        orderLocalDataSource.updateOrderStatus(orderId, orderStatus)

    override fun getOrderDetails(orderId: String): Flow<Order> =
        orderLocalDataSource.getOrderDetails(orderId)

    override suspend fun fetchOrderDetails(orderId: String): Result<Boolean, DataError> =
        when(val remoteResult = orderRemoteDataSource.getOrderDetails(orderId)){
            is Result.Success -> {
                val orderDetails = remoteResult.data
                orderLocalDataSource.updateOrderDetails(orderDetails.id, orderDetails.price, orderDetails.notes)
                Result.Success(true)
            }
            is Result.Error -> {
                Result.Error(remoteResult.error)
            }
        }
}