package com.thusee.imagecard.domain.repository

import com.thusee.imagecard.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    suspend fun getCategory(): Flow<List<Category>>
}