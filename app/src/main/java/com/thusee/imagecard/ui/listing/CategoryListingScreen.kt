package com.thusee.imagecard.ui.listing

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.thusee.imagecard.domain.model.Category
import com.thusee.imagecard.domain.model.Product
import com.thusee.imagecard.domain.model.SalePrice
import com.thusee.imagecard.ui.navigation.NavigationScreen
import timber.log.Timber

@Composable
fun CategoryListingScreen(
    modifier: Modifier = Modifier,
    viewModel: SharedViewModel,
    navController: NavController
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        val categoryState = viewModel.categoryState.collectAsState()

        when (val state = categoryState.value) {
            is UIState.Loading -> {}
            is UIState.Success -> {
                Timber.d("Response : ${state.data}")
                ProductList(
                    categories = state.data,
                    navController = navController,
                    viewModel = viewModel
                )
            }

            is UIState.Error -> {
                Timber.d("Error ${state.exception.message}")
            }

            is UIState.Empty -> {}
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductList(
    categories: List<Category>,
    navController: NavController? = null,
    viewModel: SharedViewModel? = null,
) {
    LazyColumn {
        categories.forEach { category ->
            stickyHeader {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {

                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = category.name,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            items(category.products) { product ->
                ProductCardItem(product = product) {
                    viewModel?.selectProduct(it)
                    navController?.navigate(
                        route = NavigationScreen.Details.route
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ProductListPreview() {
    val categoriesData = listOf(
        Category(
            name = "Food",
            products = listOf(
                Product(
                    name = "Bread",
                    url = "/Bread.jpg",
                    description = "",
                    salePrice = SalePrice(
                        amount = "0.81",
                        currency = "EUR"
                    )
                ),
                Product(
                    name = "Sandwich",
                    url = "/Sandwich.jpg",
                    description = "",
                    salePrice = SalePrice(
                        amount = "2.01",
                        currency = "EUR"
                    )
                )
            )
        )
    )

    ProductList(categories = categoriesData)
}