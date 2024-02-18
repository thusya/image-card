package com.thusee.imagecard.data.model.mapper

import com.thusee.imagecard.data.model.response.CategoryResponse
import com.thusee.imagecard.data.model.response.ProductResponse
import com.thusee.imagecard.data.model.response.SalePriceResponse
import com.thusee.imagecard.domain.model.Category
import com.thusee.imagecard.domain.model.Product
import com.thusee.imagecard.domain.model.SalePrice

fun CategoryResponse.toCategory(): Category {
    val products = products?.map { it.toProduct() } ?: emptyList()

    return Category(
        id = id.orEmpty(),
        name = name.orEmpty(),
        description = description.orEmpty(),
        products = products
    )
}

fun ProductResponse.toProduct(): Product = Product(
    id = id.orEmpty(),
    categoryId = categoryId.orEmpty(),
    name = name.orEmpty(),
    url = url.orEmpty(),
    description = description.orEmpty(),
    salePrice = salePrice?.toSalePrice() ?: SalePrice()
)

fun SalePriceResponse.toSalePrice(): SalePrice = SalePrice(
    amount = amount.orEmpty(),
    currency = currency.orEmpty()
)