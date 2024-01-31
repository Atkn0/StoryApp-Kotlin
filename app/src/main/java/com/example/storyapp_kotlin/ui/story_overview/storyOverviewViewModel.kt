package com.example.storyapp_kotlin.ui.story_overview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storyapp_kotlin.di.retrofit.ImageRequestBody
import com.example.storyapp_kotlin.di.retrofit.apiService
import com.example.storyapp_kotlin.domain.usecase.AddCreatedStoryFirestoreUseCase
import com.example.storyapp_kotlin.domain.usecase.GetCompleteStoriesUseCase
import com.example.storyapp_kotlin.models.StoryModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class storyOverviewViewModel @Inject constructor(
    private val apiService: apiService,
    private val addCreatedStoryFirestoreUseCase: AddCreatedStoryFirestoreUseCase,
    private val getCompleteStoriesUseCase: GetCompleteStoriesUseCase,
) : ViewModel() {

    val isProgressSuccess = MutableLiveData<Boolean>()
    val storyImageUrl = MutableLiveData<String?>()
    val surpiseStory = MutableLiveData<StoryModel?>()
    val isAddStorySuccess = MutableLiveData<Boolean?>().apply { value = false }

    init {
        isProgressSuccess.value = false
    }

    fun makeApiCallImage(storyContent : String) = viewModelScope.launch {
        try {

            val request = ImageRequestBody(text = storyContent + "Create digital mysterious artwork from this story")
            val response = apiService.getTextToImageResponse(request)

            if (response.isSuccessful) {
                isProgressSuccess.value = true
                val responseBody = response.body()
                storyImageUrl.value = responseBody?.generated_image
            } else {
                val errorMessage = response.errorBody()?.string()
                println(errorMessage)
            }
        }
        catch (e : Exception){
            println(e.localizedMessage)
        }
    }

    suspend fun addCreatedStoryToFirestore(storyTitle:String,storyContent : String){
        try {
            val result = addCreatedStoryFirestoreUseCase(storyTitle = storyTitle ,storyContent, storyImageUrl.value.toString())
            isAddStorySuccess.postValue(result)
        } catch (e: Exception) {
            println(e.localizedMessage)
            isAddStorySuccess.postValue(false)
        }
    }

    fun getSurpriseStory() = viewModelScope.launch {
        val storiesList = getCompleteStoriesUseCase()
        getRandomStory(storiesList)
    }

    private fun getRandomStory(storiesList : ArrayList<StoryModel>){
        val randomIndex = (0..<storiesList.size).random()
        val randomStory = storiesList[randomIndex]
        surpiseStory.value = randomStory
    }

    fun clearData() {
        isProgressSuccess.value = false
        storyImageUrl.value = null
        surpiseStory.value = null
        isAddStorySuccess.value = false
    }




}