package com.example.storyapp_kotlin.di.retrofit

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface apiService {
    @Headers(
        "content-type: application/json",
        "X-RapidAPI-Key: 004539b61emshc0462a091ea5c9fp15faf1jsn17216ff0eb63",
        "X-RapidAPI-Host: chatgpt-42.p.rapidapi.com"
    )
    @POST("texttoimage")
    suspend fun getTextToImageResponse(@Body request: ImageRequestBody): Response<ImageResponseBody>
}