package com.example.storyapp_kotlin.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.example.storyapp_kotlin.data.repository.FirebaseRepository
import com.example.storyapp_kotlin.models.UserModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllUsersUseCase @Inject constructor(private val firebaseRepository: FirebaseRepository) {

    suspend operator fun invoke(): ArrayList<UserModel> {
        return firebaseRepository.getAllUsers()
    }

}