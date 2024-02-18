package com.thusee.imagecard.domain.model

data class Product(
    val id: String = "",
    val categoryId: String = "",
    val name: String = "",
    val url: String = "",
    val description: String = "",
    val salePrice: SalePrice = SalePrice()
)
