package com.example.storyapp_kotlin.di.modules

import com.example.storyapp_kotlin.data.repository.FirebaseRepository
import com.example.storyapp_kotlin.domain.usecase.GetAllUsersUseCase
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
    fun provideGetAllUsersUseCase(firebaseRepository: FirebaseRepository) : GetAllUsersUseCase {
        return GetAllUsersUseCase(firebaseRepository)
    }

}