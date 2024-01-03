package com.example.storyapp_kotlin.models

import android.os.Parcelable
import com.google.firebase.Timestamp
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import java.util.UUID

@Parcelize
data class StoryModel(
    var storyId : String? = null,
    val storyContent : String? = null,
    val contributions : ArrayList<String>? = null,
    val createdDate : Timestamp? = null,
    val numberOfReader : Int? = null,
    val numberOfLikes : Int? = null,
    val isFinished : Boolean? = null,
) : Parcelable
