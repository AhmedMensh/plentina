package com.android.plentinatask.models


import com.squareup.moshi.Json


data class BooksResponseModel(
    @field:Json(name = "author")
    val author: String ? = null,
    @field:Json(name = "available")
    val available: Boolean ? = null,
    @field:Json(name = "current-stock")
    val currentStock: Int? = null,
    @field:Json(name = "id")
    val id: Int? = null,
    @field:Json(name = "isbn")
    val isbn: String? = null,
    @field:Json(name = "name")
    val name: String? = null,
    @field:Json(name = "price")
    var price: Double? = null,
    @field:Json(name = "type")
    val type: String? = null
)
