package com.example.fooddelivery.di

import com.example.fooddelivery.data.database.dao.OrdersDAO
import com.example.fooddelivery.data.source.local.OrderLocalDataSourceImpl
import com.example.fooddelivery.domain.data_source.order.OrderLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LocalDataSourceModule {

    @Provides
    @Singleton
    fun provideOrdersLocalDataSource(orderDao: OrdersDAO): OrderLocalDataSource {
        return OrderLocalDataSourceImpl(orderDao)
    }

}