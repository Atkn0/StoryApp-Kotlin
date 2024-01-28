package com.example.storyapp_kotlin.models

import android.os.Parcelable
import com.google.firebase.Timestamp
import kotlinx.parcelize.Parcelize

@Parcelize
data class StoryModel(
    var storyId: String? = null,
    var storyTitle: String? = null,
    val storyImageUrl: String? = null,
    val storyContent: HashMap<String, String>? = null,
    val contributions: ArrayList<String>? = null,
    val createdDate: Timestamp? = null,
    val numberOfReader: Int? = null,
    val numberOfLikes: Int? = null,
    val isFinished: Boolean? = null,
) : Parcelable
