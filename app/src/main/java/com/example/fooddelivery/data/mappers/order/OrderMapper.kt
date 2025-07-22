package com.example.fooddelivery.data.mappers.order

import com.example.fooddelivery.data.database.entities.OrderEntity
import com.example.fooddelivery.data.network.dto.order.OrderDTO
import com.example.fooddelivery.data.network.dto.order.OrderDetailsDTO
import com.example.fooddelivery.domain.models.order.Order

fun OrderDTO.toOrder(): Order {
    return Order(
        id = id,
        customerName = customerName,
        restaurant = restaurant,
        status = status
    )
}

fun OrderDTO.toOrderEntity(): OrderEntity {
    return OrderEntity(
        id = id,
        customerName = customerName,
        restaurant = restaurant,
        status = status
    )
}

fun OrderEntity.toOrder(): Order {
    return Order(
        id = id,
        customerName = customerName,
        restaurant = restaurant,
        status = status,
        price = price,
        notes = notes
    )
}

fun Order.toOrderEntity(): OrderEntity {
    return OrderEntity(
        id = id,
        customerName = customerName,
        restaurant = restaurant,
        status = status
    )
}

fun OrderDetailsDTO.toOrder(): Order {
    return Order(
        id = id,
        customerName = customerName,
        restaurant = restaurant,
        status = status,
        price = price,
        notes = notes
    )
}