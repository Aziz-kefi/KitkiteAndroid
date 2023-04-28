package com.example.kitkite.models

data class User(
    val _id: String,
    val firstname: String,
    val lastname: String,
    val email: String,
    val isVerified: Boolean,
    val imageUrl: String
)