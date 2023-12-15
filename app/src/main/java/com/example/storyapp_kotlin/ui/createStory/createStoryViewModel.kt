package com.example.storyapp_kotlin.ui.createStory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storyapp_kotlin.domain.usecase.AddCreatedStoryFirestoreUseCase
import com.example.storyapp_kotlin.domain.usecase.GetUserByIdUseCase
import com.example.storyapp_kotlin.models.StoryModel
import com.example.storyapp_kotlin.models.UserModel
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class createStoryViewModel @Inject constructor(
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val addCreatedStoryFirestoreUseCase: AddCreatedStoryFirestoreUseCase,
    private val firestore : FirebaseFirestore,
    private val auth : FirebaseAuth,
): ViewModel() {

    private val users_ref = firestore.collection("Users")
    private val completeTheStory_ref = firestore.collection("completeTheStory")


    suspend fun createStory(storyContent : String){

        val userUID = auth.currentUser!!.uid
        val hasEnoughCredit = checkUserCredit(userUID)

        if (hasEnoughCredit){ addCreatedStoryToFirestore(userUID,storyContent) }
        else{ println("User has not enough credit") }

    }

    private suspend fun checkUserCredit(userUID : String) : Boolean{
        return try {
            val userModel = getUserByIdUseCase(userUID)!!
            userModel.storyCreationCredit > 0
        }
        catch (e : Exception){
            println("Error in checkUserCredit function")
            false
        }

    }
    private suspend fun addCreatedStoryToFirestore(userUID : String,storyContent: String){

        viewModelScope.launch {
            val addCreateIsSuccess = async {addCreatedStoryFirestoreUseCase(storyContent,userUID)}.await()
        }




    }




}