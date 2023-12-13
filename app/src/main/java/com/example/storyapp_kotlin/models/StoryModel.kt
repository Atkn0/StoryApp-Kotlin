package com.example.storyapp_kotlin.models

import com.google.firebase.Timestamp
import java.util.UUID

data class StoryModel(
    var storyId : String = UUID.randomUUID().toString(),
    val storyContent : String? = null,
    val contributions : ArrayList<String>? = null,
    val createdDate : Timestamp? = null,
    val numberOfReader : Int? = null,
    val numberOfLikes : Int? = null,
    val isFinished : Boolean? = null,
)
