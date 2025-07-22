package com.example.fooddelivery.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fooddelivery.data.database.entities.OrderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OrdersDAO {

    @Query("SELECT * FROM orders")
    fun getOrders(): Flow<List<OrderEntity>>

    @Query("SELECT * FROM orders")
    suspend fun getAllOrdersAsList(): List<OrderEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrders(orders: List<OrderEntity>)

    @Delete
    suspend fun deleteOrders(orders: List<OrderEntity>)

    @Query("UPDATE orders SET status = :orderStatus WHERE id = :orderId")
    fun updateOrderStatus(orderId: String, orderStatus: String)

    @Query("SELECT * FROM orders WHERE id = :orderId")
    fun getOrderDetails(orderId: String): Flow<OrderEntity>

    @Query("UPDATE orders SET price = :price, notes = :notes WHERE id = :orderId")
    suspend fun updateOrderDetails(orderId: String, price: String, notes: String)

}