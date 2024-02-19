package com.thusee.imagecard.data.repository

import com.thusee.imagecard.data.model.mapper.toCategory
import com.thusee.imagecard.data.network.ApiService
import com.thusee.imagecard.domain.model.Category
import com.thusee.imagecard.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    CategoryRepository {

    override fun getCategory(): Flow<List<Category>> = flow {
        val result = apiService.getCategories().map { it.toCategory() }
        emit(result)
    }
}