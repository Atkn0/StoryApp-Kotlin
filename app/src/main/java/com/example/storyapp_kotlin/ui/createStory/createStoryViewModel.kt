package com.example.storyapp_kotlin.ui.createStory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storyapp_kotlin.di.retrofit.ImageRequestBody
import com.example.storyapp_kotlin.di.retrofit.apiService
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
    private val apiService: apiService
): ViewModel() {

    private val users_ref = firestore.collection("Users")
    private val completeTheStory_ref = firestore.collection("completeTheStory")

    val userCreditControl = MutableLiveData<Boolean>()
    private lateinit var userUID : String
    private val _wordCount = MutableLiveData<Int>()
    val wordCount: LiveData<Int>
        get() = _wordCount

    private val _isCreateButtonEnabled = MutableLiveData<Boolean>()
    val isCreateButtonEnabled : LiveData<Boolean>
        get() = _isCreateButtonEnabled

    init {
        _wordCount.value = 0
        _isCreateButtonEnabled.value = true
        userUID = auth.currentUser!!.uid
    }




    suspend fun checkUserCredit(){
        try {
            val userModel = getUserByIdUseCase(userUID)!!
            println("check user credit try true")
            userCreditControl.value = userModel.storyCreationCredit >= 2
        }
        catch (e : Exception){
            println("Error in checkUserCredit function")
        }

    }

    fun updateWordCount(storyContent: String) {
        val words = storyContent.trim().split("[\\s.?/-]+".toRegex())
        _wordCount.value = words.size
        _isCreateButtonEnabled.value = wordCount.value!! <= 50
    }

     suspend fun checkUserCredit(userUID : String) : Boolean{
        return try {
            val userModel = getUserByIdUseCase(userUID)!!
            userModel.storyCreationCredit > 0
        }
        catch (e : Exception){
            println("Error in checkUserCredit function")
            false
        }

    }




}