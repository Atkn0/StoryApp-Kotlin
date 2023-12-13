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


   suspend fun createStory(storyContent : String){

       val userUID = auth.currentUser!!.uid
       val hasEnoughCredit = checkUserCredit(userUID)

       if (hasEnoughCredit){ addCreatedStoryToFirestore(userUID,storyContent) }
       else{ println("User has not enough credit") }

    }


    private suspend fun checkUserCredit(userUID : String) : Boolean{
        return try {

            val documentSnapshot = users_ref.document(userUID).get().await()
            println("documentSnapshot : $documentSnapshot")
            val userModel = UserModel(
                userId = documentSnapshot.getString("userId")!!,
                email = documentSnapshot.getString("email")!!,
                storyAddCredit = documentSnapshot.getLong("storyAddCredit")!!.toInt(),
                storyCreationCredit = documentSnapshot.getLong("storyCreationCredit")!!.toInt()
            )

            userModel.storyCreationCredit > 0
        }
        catch (e : Exception){
            println("Error in checkUserCredit function")
            false
        }

    }
    private fun addCreatedStoryToFirestore(userUID : String,storyContent: String){

        val storyModel = StoryModel(
            storyContent = storyContent,
            contributions = arrayListOf(userUID),
            numberOfReader = 0,
            numberOfLikes = 0,
            isFinished = false,
            createdDate = Timestamp.now()
        )

        val storyID = storyModel.storyId

        completeTheStory_ref
            .document(storyID)
            .set(storyModel)
            .addOnSuccessListener {
                println("Story Created")
            }
            .addOnFailureListener {
                println("Story Creation Failed")
            }

    }


}