package com.example.kitkite.models

import java.util.*

data class Comment(
    val id: String,
    val user: User,
    val content: String,
    val createdAt: Date
)

