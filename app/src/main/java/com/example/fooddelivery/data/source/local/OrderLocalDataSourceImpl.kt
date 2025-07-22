package com.example.fooddelivery.data.source.local

import com.example.fooddelivery.data.database.dao.OrdersDAO
import com.example.fooddelivery.data.database.entities.OrderEntity
import com.example.fooddelivery.data.mappers.order.toOrder
import com.example.fooddelivery.data.mappers.order.toOrderEntity
import com.example.fooddelivery.domain.data_source.order.OrderLocalDataSource
import com.example.fooddelivery.domain.models.order.Order
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OrderLocalDataSourceImpl @Inject constructor(
    private val ordersDAO: OrdersDAO
) : OrderLocalDataSource{

    override fun getOrders(): Flow<List<Order>> =
        ordersDAO.getOrders().map { it.map { it.toOrder() } }

    override suspend fun updateOrders(ordersList: List<Order>) {
        val currentLocalOrdersMap = ordersDAO.getAllOrdersAsList()
            .associateBy { it.id }

        val ordersToUpsert = mutableListOf<OrderEntity>()

        for (newOrder in ordersList) {
            val localOrderEntity = currentLocalOrdersMap[newOrder.id]

            if (localOrderEntity != null) {
                val mergedOrderEntity = localOrderEntity.copy(
                    customerName = newOrder.customerName,
                    restaurant = newOrder.restaurant,
                    status = newOrder.status
                )
                ordersToUpsert.add(mergedOrderEntity)
            } else {
                ordersToUpsert.add(newOrder.toOrderEntity())
            }
        }

        if (ordersToUpsert.isNotEmpty()) {
            ordersDAO.insertOrders(ordersToUpsert)
        }

        val remoteOrderIds = ordersList.map { it.id }.toSet()
        val localOrdersToDelete = currentLocalOrdersMap.values.filter { it.id !in remoteOrderIds }
        if (localOrdersToDelete.isNotEmpty()) {
            ordersDAO.deleteOrders(localOrdersToDelete)
        }
    }

    override fun updateOrderStatus(orderId: String, orderStatus: String) =
        ordersDAO.updateOrderStatus(orderId, orderStatus)

    override fun getOrderDetails(orderId: String): Flow<Order> =
        ordersDAO.getOrderDetails(orderId).map { it.toOrder() }

    override suspend fun updateOrderDetails(
        orderId: String,
        price: String,
        notes: String
    ) {
        ordersDAO.updateOrderDetails(orderId, price, notes)
    }

}