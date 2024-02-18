package com.thusee.imagecard.di

import com.thusee.imagecard.data.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideCategoryApi(
        retrofit: Retrofit
    ): ApiService = retrofit.create(ApiService::class.java)
}