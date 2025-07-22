package com.example.fooddelivery.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.example.fooddelivery.BuildConfig
import com.example.fooddelivery.data.network.OrdersApiService
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideOrdersApiService(retrofit: Retrofit): OrdersApiService {
        return retrofit.create(OrdersApiService::class.java)
    }

    @Singleton
    @Provides
    fun okHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client: OkHttpClient.Builder = OkHttpClient.Builder()
            .hostnameVerifier { _, _ -> true }
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)

        client.addInterceptor(loggingInterceptor)

        client.addInterceptor { chain: Interceptor.Chain ->
            val originalRequest = chain.request()
            val builder = originalRequest.newBuilder()
            builder.addHeader(
                "Accept",
                "application/json"
            )
            val newRequest = builder.build()
            chain.proceed(newRequest)
        }

        return client.build()
    }

}