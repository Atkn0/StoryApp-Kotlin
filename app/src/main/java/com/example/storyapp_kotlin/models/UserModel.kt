package com.example.storyapp_kotlin.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserModel(
    val userId: String,
    val email: String,
    val storyAddCredit: Int = 6,
    val storyCreationCredit: Int = 2,
    ) : Parcelable
