package com.example.fooddelivery.domain.use_case.order

import com.example.fooddelivery.domain.repository.order.OrdersRepository
import javax.inject.Inject

class GetOrdersUseCase @Inject constructor(
    private val ordersRepository: OrdersRepository
) {
    fun getOrder() = ordersRepository.getOrders()
    suspend fun fetchLatestOrders() = ordersRepository.fetchLatestOrders()
}