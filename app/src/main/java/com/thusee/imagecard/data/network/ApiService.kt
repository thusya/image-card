package com.thusee.imagecard.data.network

import com.thusee.imagecard.data.model.response.CategoryResponse
import retrofit2.http.GET

interface ApiService {

    @GET("/")
    suspend fun getCategories(): List<CategoryResponse>
}