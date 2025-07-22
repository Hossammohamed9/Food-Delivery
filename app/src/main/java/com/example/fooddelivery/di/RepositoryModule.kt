package com.example.fooddelivery.di

import com.example.fooddelivery.data.repository.order.OrdersRepositoryImpl
import com.example.fooddelivery.domain.data_source.order.OrderLocalDataSource
import com.example.fooddelivery.domain.data_source.order.OrderRemoteDataSource
import com.example.fooddelivery.domain.repository.order.OrdersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideOrdersRepository(orderRemoteDataSource: OrderRemoteDataSource, orderLocalDataSource: OrderLocalDataSource): OrdersRepository{
        return OrdersRepositoryImpl(orderRemoteDataSource, orderLocalDataSource)
    }

}