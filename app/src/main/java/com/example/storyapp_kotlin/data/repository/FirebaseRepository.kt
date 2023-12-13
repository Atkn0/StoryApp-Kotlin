package com.example.storyapp_kotlin.data.repository

import com.example.storyapp_kotlin.models.UserModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FirebaseRepository @Inject constructor(){

    private val db = Firebase.firestore

    suspend fun getAllUsers() : ArrayList<UserModel>{
        val userCollection = db.collection("Users").get().await()

        val userList = arrayListOf<UserModel>()
        for (document in userCollection){
            val userModel = UserModel(
                userId = document.getString("userId")!!,
                email = document.getString("email")!!,
                storyAddCredit = document.getLong("storyAddCredit")!!.toInt(),
                storyCreationCredit = document.getLong("storyCreationCredit")!!.toInt()
            )
            userList.add(userModel)
        }
        return userList
    }

}