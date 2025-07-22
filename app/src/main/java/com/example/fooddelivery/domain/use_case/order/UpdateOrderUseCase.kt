package com.example.fooddelivery.domain.use_case.order

import com.example.fooddelivery.domain.models.order.OrderUpdate
import com.example.fooddelivery.domain.repository.order.OrdersRepository
import javax.inject.Inject

class UpdateOrderUseCase @Inject constructor(
    private val ordersRepository: OrdersRepository
) {
    fun updateOrderStatus(orderUpdate: OrderUpdate) =
        ordersRepository.updateOrderStatus(orderUpdate.orderId, orderUpdate.status)
}