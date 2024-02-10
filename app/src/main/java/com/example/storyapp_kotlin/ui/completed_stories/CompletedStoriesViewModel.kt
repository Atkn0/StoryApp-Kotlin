package com.example.storyapp_kotlin.ui.completed_stories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storyapp_kotlin.domain.usecase.GetAllUsersUseCase
import com.example.storyapp_kotlin.domain.usecase.GetStoriesByCollectionUseCase
import com.example.storyapp_kotlin.models.StoryModel
import com.example.storyapp_kotlin.models.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class CompletedStoriesViewModel @Inject constructor(
    private val getStoriesByCollectionUseCase: GetStoriesByCollectionUseCase,
    private val getAllUsersUseCase: GetAllUsersUseCase
): ViewModel() {

    private val _storyList = MutableLiveData<ArrayList<StoryModel>>()
    val storyList: LiveData<ArrayList<StoryModel>> get() = _storyList

    private val _userList = MutableLiveData<ArrayList<UserModel>>()
    val userList: LiveData<ArrayList<UserModel>> get() = _userList

    val combinedLiveData = MediatorLiveData<Pair<ArrayList<StoryModel>?, ArrayList<UserModel>?>>()
    init {
        combinedLiveData.addSource(storyList) { storyListValue->
            // Her iki LiveData'dan gelen veri burada kontrol edilecek
            val userListValue = userList.value
            combinedLiveData.value = Pair(storyListValue, userListValue)
        }

        combinedLiveData.addSource(userList) { userListValue ->
            // Her iki LiveData'dan gelen veri burada kontrol edilecek
            val storyListValue = storyList.value
            combinedLiveData.value = Pair(storyListValue, userListValue)
        }
    }


    fun getStoriesByCollection(collectionName: String) {
        viewModelScope.launch {
            val storyList = getStoriesByCollectionUseCase(collectionName)
            _storyList.postValue(storyList)
        }
    }

    fun getAllUsers() {
        viewModelScope.launch {
            val users = getAllUsersUseCase()
            _userList.postValue(users)
        }
    }

}