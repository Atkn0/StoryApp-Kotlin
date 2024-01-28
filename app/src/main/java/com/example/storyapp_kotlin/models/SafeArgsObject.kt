package com.example.storyapp_kotlin.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SafeArgsObject(
    var storyTitle : String,
    var storyContent : String
) : Parcelable