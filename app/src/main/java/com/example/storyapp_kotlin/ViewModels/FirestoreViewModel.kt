package com.example.storyapp_kotlin.ViewModels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.storyapp_kotlin.Models.StoryModel
import com.example.storyapp_kotlin.Models.UserModel
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.auth
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class FirestoreViewModel() : ViewModel() {
    val db = Firebase.firestore
    val auth = Firebase.auth

    val completeTheStory_ref = db.collection("completeTheStory")
    val users_ref = db.collection("Users")


    suspend fun createStory(storyContent : String, context : Context){

        // Bu kısım viewModel içerisinde de halledilebilir tekrar bak!
        val userUID = auth.currentUser!!.uid
        val hasEnoughCredit = checkUserCredit(userUID)

        if (hasEnoughCredit){ addCreatedStoryToFirestore(userUID,storyContent,context) }
        // Toast message ile uyarı ekle!
        else{ Toast.makeText(context,"You don't have enough credit to create a story",Toast.LENGTH_SHORT).show() }

    }
    private suspend fun checkUserCredit(userUID : String) : Boolean{
        return try {
            val documentSnapshot = users_ref.document(userUID).get().await()
            val userModel = UserModel(
                userId = documentSnapshot.getString("userId")!!,
                email = documentSnapshot.getString("email")!!,
                storyAddCredit = documentSnapshot.getLong("storyAddCredit")!!.toInt(),
                storyCreationCredit = documentSnapshot.getLong("storyCreationCredit")!!.toInt()
            )

            userModel.storyCreationCredit > 0
        }
        catch (e : Exception){
            println(e.message)
            false
        }

    }
    private fun addCreatedStoryToFirestore(userUID : String,storyContent: String,context: Context){

        val storyModel = StoryModel(
            storyContent = storyContent,
            contributions = listOf(userUID),
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
                createToastMessage(context = context,message = "Story Created")
            }
            .addOnFailureListener {
                createToastMessage(context = context,message = "Story Creation Failed")
            }

    }

    fun createToastMessage(context : Context,message : String){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }
}