package com.example.storyapp_kotlin.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storyapp_kotlin.data.repository.FirebaseRepository
import com.example.storyapp_kotlin.domain.usecase.GetAllUsersUseCase
import com.example.storyapp_kotlin.models.UserModel
import com.example.storyapp_kotlin.ui.login.AuthViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfilePageViewModel @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val firebaseRepository: FirebaseRepository,
) : ViewModel() {

    var userSignStatus = MutableLiveData<Boolean>()



}