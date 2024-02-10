package com.example.storyapp_kotlin.di.retrofit

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface apiService {
    @Headers(
        "content-type: application/json",
        "X-RapidAPI-Key: YOUR_API_KEY",
        "X-RapidAPI-Host: open-ai21.p.rapidapi.com"
    )
    @POST("texttoimage2")
    suspend fun getTextToImageResponse(@Body request: ImageRequestBody): Response<ImageResponseBody>
}
