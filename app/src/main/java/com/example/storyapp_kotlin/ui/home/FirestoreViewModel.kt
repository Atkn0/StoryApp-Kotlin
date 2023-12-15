package com.example.storyapp_kotlin.ui.home

import androidx.lifecycle.ViewModel
import com.example.storyapp_kotlin.models.StoryModel
import com.example.storyapp_kotlin.models.UserModel
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class FirestoreViewModel : ViewModel() {
    //try this implementation with dagger hilt!
    val db = Firebase.firestore
    val auth = Firebase.auth

    val completeTheStory_ref = db.collection("completeTheStory")
    val users_ref = db.collection("Users")






}