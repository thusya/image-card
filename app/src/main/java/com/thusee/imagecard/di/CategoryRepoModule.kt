package com.thusee.imagecard.di

import com.thusee.imagecard.data.repository.CategoryRepositoryImpl
import com.thusee.imagecard.domain.repository.CategoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class CategoryRepoModule {

    @Binds
    abstract fun bindCategoryRepo(
        categoryRepositoryImpl: CategoryRepositoryImpl
    ): CategoryRepository
}