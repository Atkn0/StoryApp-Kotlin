package com.example.storyapp_kotlin.di.modules

import com.example.storyapp_kotlin.data.repository.FirebaseRepository
import com.example.storyapp_kotlin.domain.usecase.AddCreatedStoryFirestoreUseCase
import com.example.storyapp_kotlin.domain.usecase.GetAllUsersUseCase
import com.example.storyapp_kotlin.domain.usecase.GetCompleteStoriesUseCase
import com.example.storyapp_kotlin.domain.usecase.GetCompleteStoryByIDUseCase
import com.example.storyapp_kotlin.domain.usecase.GetUserByIdUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseRepo() : FirebaseRepository{
        return FirebaseRepository()
    }

    @Provides
    @Singleton
    fun provideFirebaseFirestore() : FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth() : FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideGetAllUsersUseCase(firebaseRepository: FirebaseRepository) : GetAllUsersUseCase {
        return GetAllUsersUseCase(firebaseRepository)
    }
    @Provides
    @Singleton
    fun provideGetCompleteStoriesUseCase(firebaseRepository: FirebaseRepository) : GetCompleteStoriesUseCase {
        return GetCompleteStoriesUseCase(firebaseRepository)
    }

    @Provides
    @Singleton
    fun provideGetUserByIdUseCase(firebaseRepository: FirebaseRepository) : GetUserByIdUseCase {
        return GetUserByIdUseCase(firebaseRepository)
    }

    @Provides
    @Singleton
    fun provideAddCreateStoryUseCase(firebaseRepository: FirebaseRepository) : AddCreatedStoryFirestoreUseCase {
        return AddCreatedStoryFirestoreUseCase(firebaseRepository)
    }

    @Provides
    @Singleton
    fun provideGetCompleteStoryByIDUseCase(firebaseRepository: FirebaseRepository) : GetCompleteStoryByIDUseCase {
        return GetCompleteStoryByIDUseCase(firebaseRepository)
    }

}