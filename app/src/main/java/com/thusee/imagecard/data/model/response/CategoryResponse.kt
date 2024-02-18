package com.thusee.imagecard.data.model.response

import com.squareup.moshi.Json

data class CategoryResponse(
    @field:Json(name = "id")
    val id: String? = null,
    @field:Json(name = "name")
    val name: String? = null,
    @field:Json(name = "description")
    val description: String? = null,
    @field:Json(name = "products")
    val products: List<ProductResponse>? = null
)

data class ProductResponse(
    @field:Json(name = "id")
    val id: String? = null,
    @field:Json(name = "categoryId")
    val categoryId: String? = null,
    @field:Json(name = "name")
    val name: String? = null,
    @field:Json(name = "url")
    val url: String? = null,
    @field:Json(name = "description")
    val description: String? = null,
    @field:Json(name = "salePrice")
    val salePrice: SalePriceResponse? = null
)

data class SalePriceResponse(
    @field:Json(name = "amount")
    val amount: String? = null,
    @field:Json(name = "currency")
    val currency: String? = null
)
