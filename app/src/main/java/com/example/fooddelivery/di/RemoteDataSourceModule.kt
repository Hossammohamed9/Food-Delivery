package com.example.fooddelivery.di

import com.example.fooddelivery.data.network.OrdersApiService
import com.example.fooddelivery.data.source.remote.OrderRemoteDataSourceImpl
import com.example.fooddelivery.domain.data_source.order.OrderRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RemoteDataSourceModule {

    @Provides
    @Singleton
    fun provideOrdersRemoteDataSource(ordersApiService: OrdersApiService): OrderRemoteDataSource {
        return OrderRemoteDataSourceImpl(ordersApiService)
    }

}