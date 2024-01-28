package com.example.storyapp_kotlin.domain.usecase

import com.example.storyapp_kotlin.data.repository.FirebaseRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddCreatedStoryFirestoreUseCase @Inject constructor(private val firebaseRepository: FirebaseRepository) {

    suspend operator fun invoke(storyContent: String,storyImageUrl : String) : Boolean {
        return firebaseRepository.addCreatedStoryFirestore(storyContent,storyImageUrl)
    }

}