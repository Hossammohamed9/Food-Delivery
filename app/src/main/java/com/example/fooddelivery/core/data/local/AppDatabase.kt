package com.example.fooddelivery.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fooddelivery.data.database.dao.OrdersDAO
import com.example.fooddelivery.data.database.entities.OrderEntity

@Database(entities = [OrderEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun orderDao() : OrdersDAO

}