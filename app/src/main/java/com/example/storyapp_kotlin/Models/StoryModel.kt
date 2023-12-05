package com.example.storyapp_kotlin.Models

import com.google.firebase.Timestamp
import java.util.UUID

data class StoryModel(
    var storyId : String = UUID.randomUUID().toString(),
    val storyContent : String? = null,
    val contributions : List<String>? = null,
    val createdDate : Timestamp? = null,
    val numberOfReader : Int? = null,
    val numberOfLikes : Int? = null,
    val isFinished : Boolean? = null,
)
