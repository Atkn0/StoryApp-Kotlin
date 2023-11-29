package com.example.storyapp_kotlin

data class UserModel(
    val userId : Int,
    val email : String,
    val storyAddCredit : Int = 6,
    val storyCreationCredit : Int = 2,

)
