package com.example.storyapp_kotlin.domain.usecase

import com.example.storyapp_kotlin.data.repository.FirebaseRepository
import com.example.storyapp_kotlin.models.StoryModel
import com.example.storyapp_kotlin.models.UserModel
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class JoinTheCurrentStoryUseCase @Inject constructor(private val firebaseRepository: FirebaseRepository){
    suspend operator fun invoke (currentStoryModel : StoryModel, newStoryContent : String): Boolean {
        return firebaseRepository.joinTheCurrentStory(currentStoryModel,newStoryContent)
    }
}