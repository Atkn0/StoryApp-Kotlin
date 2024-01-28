package com.example.storyapp_kotlin.di.retrofit

data class ImageRequestBody(
    val text: String
)
data class ImageResponseBody(
    val generated_image: String
)