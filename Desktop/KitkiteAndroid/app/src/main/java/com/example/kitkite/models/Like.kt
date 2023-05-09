package com.example.kitkite.models

import java.util.*

data class Like(
    val id: String,
    val user: User,
    val createdAt: Date
)
