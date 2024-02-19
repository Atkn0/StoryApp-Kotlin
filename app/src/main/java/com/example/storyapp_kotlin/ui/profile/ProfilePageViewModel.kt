package com.example.storyapp_kotlin.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storyapp_kotlin.data.repository.FirebaseRepository
import com.example.storyapp_kotlin.domain.usecase.GetAllUsersUseCase
import com.example.storyapp_kotlin.domain.usecase.GetUserByIdUseCase
import com.example.storyapp_kotlin.models.UserModel
import com.example.storyapp_kotlin.ui.login.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfilePageViewModel @Inject constructor(
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    val user = MutableLiveData<UserModel?>()

    fun getUserById() {
        viewModelScope.launch {
            val currentUser = firebaseAuth.currentUser?.uid
            user.value = getUserByIdUseCase(currentUser!!)
        }
    }



}