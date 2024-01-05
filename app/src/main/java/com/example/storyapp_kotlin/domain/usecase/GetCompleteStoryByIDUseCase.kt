package com.example.storyapp_kotlin.domain.usecase

import com.example.storyapp_kotlin.data.repository.FirebaseRepository
import com.example.storyapp_kotlin.models.StoryModel
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GetCompleteStoryByIDUseCase @Inject constructor(private val firebaseRepository: FirebaseRepository) {

    suspend operator fun invoke (storyID:String): StoryModel? {
        return firebaseRepository.getCompleteStoryByID(storyID)
    }

}