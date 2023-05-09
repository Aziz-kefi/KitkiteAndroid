package com.example.kitkite.models


import java.util.*

data class Post(
    val _id: String,
    val user: User,
    val content: String?,
    val imageUrl: String?,
    val comments: List<Comment>,
    val likes: List<Like>,
    val createdAt: Date,
    val updatedAt: Date

)

