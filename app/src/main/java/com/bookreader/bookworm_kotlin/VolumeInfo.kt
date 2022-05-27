package com.bookreader.bookworm_kotlin

data class VolumeInfo(
    val authors: List<String>,
    val averageRating: Double,
    val canonicalVolumeLink: String,
    val categories: List<String>,
    val contentVersion: String,
    val description: String,
    val dimensions: Dimensions,
    val imageLinks: ImageLinks,
    val industryIdentifiers: List<IndustryIdentifier>,
    val infoLink: String,
    val language: String,
    val mainCategory: String,
    val pageCount: Int,
    val printType: String,
    val publishedDate: String,
    val publisher: String,
    val ratingsCount: Int,
    val title: String
)