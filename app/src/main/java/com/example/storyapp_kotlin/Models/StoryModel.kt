package com.example.storyapp_kotlin.Models

import com.google.firebase.Timestamp

data class StoryModel(
    val storyId : Int? = null,
    val storyContent : String? = null,
    val contribitions : List<UserModel>? = null,
    val createdDate : Timestamp? = null,
    val numberOfReader : Int? = null,
    val numberOfLikes : Int? = null,
    val isFinished : Boolean? = null,
)
