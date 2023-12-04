package com.example.storyapp_kotlin.Models

data class UserModel(
    val userId: String,
    val email: String,
    val storyAddCredit: Int = 6,
    val storyCreationCredit: Int = 2,

    )
