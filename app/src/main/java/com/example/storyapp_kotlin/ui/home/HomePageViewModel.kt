package com.example.storyapp_kotlin.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storyapp_kotlin.domain.usecase.GetAllUsersUseCase
import com.example.storyapp_kotlin.models.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(private val getAllUsersUseCase: GetAllUsersUseCase): ViewModel() {

    var usersList : MutableLiveData<ArrayList<UserModel>?> = MutableLiveData()

    suspend fun getAllUsers(){
        viewModelScope.launch {
            usersList.value = getAllUsersUseCase()
            println(usersList.value)
        }
    }

}