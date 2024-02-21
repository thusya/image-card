package com.thusee.imagecard.data.repository

import com.thusee.imagecard.data.model.response.CategoryResponse
import com.thusee.imagecard.data.model.response.ProductResponse
import com.thusee.imagecard.data.model.response.SalePriceResponse
import com.thusee.imagecard.data.network.ApiService
import com.thusee.imagecard.domain.model.Category
import com.thusee.imagecard.domain.model.Product
import com.thusee.imagecard.domain.model.SalePrice
import com.thusee.imagecard.domain.repository.CategoryRepository
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CategoryRepositoryImplTest {
    private lateinit var categoryRepo: CategoryRepository
    private val apiService: ApiService = mockk<ApiService>(relaxed = true)

    private val categoryResponse = listOf(
        CategoryResponse(
            id = null,
            name = "Food",
            products = listOf(
                ProductResponse(
                    name = "Bread",
                    url = "/Bread.jpg",
                    salePrice = SalePriceResponse(
                        amount = "0.81",
                        currency = "EUR"
                    )
                )
            )
        )
    )

    @BeforeEach
    fun before() {
        coEvery { apiService.getCategories() } returns categoryResponse

        categoryRepo = CategoryRepositoryImpl(apiService)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `getCategory emits expected data`() = runTest {
        val expectedList = listOf(
            Category(
                name = "Food",
                products = listOf(
                    Product(
                        name = "Bread",
                        url = "/Bread.jpg",
                        salePrice = SalePrice(
                            amount = "0.81",
                            currency = "EUR"
                        )
                    )
                )
            )
        )

        val result = categoryRepo.getCategory().toList().flatten()

        assertEquals(expectedList, result)
    }
}