package com.bookreader.bookworm_kotlin

data class AccessInfo(
    val accessViewStatus: String,
    val country: String,
    val embeddable: Boolean,
    val epub: Epub,
    val pdf: Pdf,
    val publicDomain: Boolean,
    val textToSpeechPermission: String,
    val viewability: String
)