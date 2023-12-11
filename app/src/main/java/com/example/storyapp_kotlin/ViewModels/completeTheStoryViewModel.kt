package com.example.storyapp_kotlin.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storyapp_kotlin.Models.StoryModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class completeTheStoryViewModel : ViewModel() {
    private val db = Firebase.firestore
    private val auth = Firebase.auth

    private val completeTheStory_ref = db.collection("completeTheStory")
    //private val users_ref = db.collection("Users")

    val storyModelLiveData = MutableLiveData<ArrayList<StoryModel>>()


    fun getAllCompleteStories(){

       completeTheStory_ref.get().addOnSuccessListener { documents ->
           val storyList = arrayListOf<StoryModel>()
           for (document in documents){
               val storyModel = StoryModel(
                     storyContent = document.getString("storyContent")!!,
                     contributions = document.get("contributions") as List<String>,
                     numberOfReader = document.getLong("numberOfReader")!!.toInt(),
                     numberOfLikes = document.getLong("numberOfLikes")!!.toInt(),
                     isFinished = document.get("finished") as Boolean?,
                     createdDate = document.getTimestamp("createdDate")!!
               )
                storyList.add(storyModel)
           }

            storyModelLiveData.value = storyList

       }

    }


}