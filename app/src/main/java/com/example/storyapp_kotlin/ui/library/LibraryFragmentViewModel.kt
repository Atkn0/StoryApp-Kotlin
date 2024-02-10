package com.example.storyapp_kotlin.ui.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storyapp_kotlin.domain.usecase.GetAllUsersUseCase
import com.example.storyapp_kotlin.domain.usecase.GetStoriesByCollectionUseCase
import com.example.storyapp_kotlin.models.StoryModel
import com.example.storyapp_kotlin.models.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LibraryFragmentViewModel @Inject constructor(
    private val getStoriesByCollectionUseCase: GetStoriesByCollectionUseCase,
    private val getAllUsersUseCase: GetAllUsersUseCase
) : ViewModel() {


    fun loadDataAndCallFunction(callback : (ArrayList<StoryModel>,ArrayList<UserModel>) -> Unit) {
        println("loadDataAndCallFunction called")
        viewModelScope.launch {
            val storyList = getStoriesByCollection("InProgressStories")
            val userList = getAllUsers()
            callback(storyList,userList)
        }

    }

    private suspend fun getStoriesByCollection(collectionName : String): ArrayList<StoryModel> {
        return withContext(Dispatchers.IO) {
            getStoriesByCollectionUseCase(collectionName)
        }
    }

    private suspend fun getAllUsers(): ArrayList<UserModel> {
        return withContext(Dispatchers.IO) {
            getAllUsersUseCase()
        }
    }

}