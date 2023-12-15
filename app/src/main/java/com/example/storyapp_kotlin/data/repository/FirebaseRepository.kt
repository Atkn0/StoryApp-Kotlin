package com.example.storyapp_kotlin.data.repository

import com.example.storyapp_kotlin.models.StoryModel
import com.example.storyapp_kotlin.models.UserModel
import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FirebaseRepository @Inject constructor(){

    private val db = Firebase.firestore
    private val auth = Firebase.auth
    private val completeTheStory_ref = db.collection("completeTheStory")



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
    suspend fun getCompleteStories() : ArrayList<StoryModel>{
        val completeTheStoryCollection = db.collection("completeTheStory").get().await()

        val completeTheStoryList = arrayListOf<StoryModel>()
        for (document in completeTheStoryCollection){
            val storyModel = StoryModel(
                storyContent = document.getString("storyContent")!!,
                contributions = document.get("contributions") as ArrayList<String>,
                numberOfReader = document.getLong("numberOfReader")!!.toInt(),
                numberOfLikes = document.getLong("numberOfLikes")!!.toInt(),
                isFinished = document.get("finished") as Boolean?,
                createdDate = document.getTimestamp("createdDate")!!
            )
            completeTheStoryList.add(storyModel)
        }
        return completeTheStoryList
    }
    suspend fun getUserByID(userID : String) : UserModel?{
        return try {
            val documentSnapshot = db.collection("Users").document(userID).get().await()
            UserModel(
                userId = documentSnapshot.getString("userId")!!,
                email = documentSnapshot.getString("email")!!,
                storyAddCredit = documentSnapshot.getLong("storyAddCredit")!!.toInt(),
                storyCreationCredit = documentSnapshot.getLong("storyCreationCredit")!!.toInt()
            )
        }
        catch (e : Exception){
            println("Error in getUserByID function")
            null
        }
    }
    suspend fun addCreatedStoryFirestore(storyContent: String, userUID: String) : Boolean {

        val storyModel = StoryModel(
            storyContent = storyContent,
            contributions = arrayListOf(userUID),
            numberOfReader = 0,
            numberOfLikes = 0,
            isFinished = false,
            createdDate = Timestamp.now()
        )

        val storyID = storyModel.storyId
        var isSuccess : Boolean = false

        completeTheStory_ref
            .document(storyID)
            .set(storyModel)
            .addOnSuccessListener {
                isSuccess = true
            }
            .addOnFailureListener {
                isSuccess = false
            }

        return isSuccess
    }

}