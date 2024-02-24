package com.thusee.imagecard.ui.listing

import com.thusee.imagecard.domain.model.Category
import com.thusee.imagecard.domain.model.Product
import com.thusee.imagecard.domain.repository.CategoryRepository
import com.thusee.imagecard.ui.util.MainCoroutineExtension
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineExtension::class)
class SharedViewModelTest {
    private lateinit var viewModel: SharedViewModel
    private val categoryRepository: CategoryRepository = mockk(relaxed = true)

    @BeforeEach
    fun setUp() {
        viewModel = SharedViewModel(categoryRepository)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `fetchCategory success updates categoryState with success`() = runTest {
        val categories = listOf(Category("1", "Food"))

        coEvery { categoryRepository.getCategory() } returns flowOf(categories)

        viewModel.fetchCategory()

        val state = viewModel.categoryState.value
        assert(state is UIState.Success)
        assertEquals(categories, (state as UIState.Success).data)
    }

    @Test
    fun `fetchCategory empty updates categoryState with empty`() = runTest {
        coEvery { categoryRepository.getCategory() } returns flowOf(emptyList())

        viewModel.fetchCategory()

        assert(viewModel.categoryState.value is UIState.Empty)
    }

    @Test
    fun `fetchCategory error updates categoryState with error`() = runTest {
        val exception = RuntimeException("Error fetching categories")
        coEvery { categoryRepository.getCategory() } returns flow { throw exception }

        viewModel.fetchCategory()

        val state = viewModel.categoryState.value
        assert(state is UIState.Error)
        assertEquals(exception, (state as UIState.Error).exception)
    }

    @Test
    fun `selectProduct updates selectedProduct`() {
        val product = Product("1", "Sandwich")

        viewModel.selectProduct(product)

        assertEquals(product, viewModel.selectedProduct)
    }
}