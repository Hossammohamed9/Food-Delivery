package com.example.fooddelivery.domain.use_case.order

import com.example.fooddelivery.domain.repository.order.OrdersRepository
import javax.inject.Inject

class GetOrderDetailsUseCase @Inject constructor(
    private val ordersRepository: OrdersRepository
) {
    fun getOrderDetails(orderId: String) = ordersRepository.getOrderDetails(orderId)
    suspend fun fetchLatestOrderDetails(orderId: String) = ordersRepository.fetchOrderDetails(orderId)
}