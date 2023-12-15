package com.example.storyapp_kotlin.domain.usecase

import com.example.storyapp_kotlin.data.repository.FirebaseRepository
import com.example.storyapp_kotlin.models.StoryModel
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GetCompleteStoriesUseCase @Inject constructor(private val firebaseRepository: FirebaseRepository) {

    suspend operator fun invoke() : ArrayList<StoryModel> {
        return firebaseRepository.getCompleteStories()
    }

}