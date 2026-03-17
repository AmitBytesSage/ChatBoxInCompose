package com.dev.equalexpert.ktor.data

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val title: String
)