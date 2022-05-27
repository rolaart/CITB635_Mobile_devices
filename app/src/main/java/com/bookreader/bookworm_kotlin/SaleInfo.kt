package com.bookreader.bookworm_kotlin

data class SaleInfo(
    val buyLink: String,
    val country: String,
    val isEbook: Boolean,
    val listPrice: ListPrice,
    val retailPrice: RetailPrice,
    val saleability: String
)