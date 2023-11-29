package com.example.storyapp_kotlin.ViewModels

import androidx.lifecycle.ViewModel
import com.example.storyapp_kotlin.UserModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.firestore

class FirestoreViewModel : ViewModel() {

    val db = Firebase.firestore

    fun addUserToDatabase(user : UserModel){


        db.collection("Users").document().set(user)
            .addOnSuccessListener {
                println("User Added")
            }
            .addOnFailureListener {
                println("User Not Added")
            }

    }


}