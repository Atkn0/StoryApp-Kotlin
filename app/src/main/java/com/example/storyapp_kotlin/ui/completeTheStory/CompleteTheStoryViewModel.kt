package com.example.storyapp_kotlin.ui.completeTheStory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storyapp_kotlin.domain.usecase.GetCompleteStoriesUseCase
import com.example.storyapp_kotlin.models.StoryModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class completeTheStoryViewModel @Inject constructor(
    private val getCompleteStoriesUseCase: GetCompleteStoriesUseCase,
    private val firestore : FirebaseFirestore,
    private val auth : FirebaseAuth
): ViewModel() {



    private val completeTheStory_ref = firestore.collection("completeTheStory")
    //private val users_ref = db.collection("Users")

    val storyModelLiveData = MutableLiveData<ArrayList<StoryModel>?>()


    fun getAllCompleteStories(){

        viewModelScope.launch {
            storyModelLiveData.value = getCompleteStoriesUseCase()
        }


    }
}