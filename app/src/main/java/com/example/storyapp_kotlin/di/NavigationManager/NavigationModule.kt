package com.example.storyapp_kotlin.di.NavigationManager

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.storyapp_kotlin.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped


@Module
@InstallIn(FragmentComponent::class)
object NavigationModule {

    @Provides
    @FragmentScoped
    fun provideNavController(activity:Activity) : NavController{
        return activity.findNavController(R.id.fragmentContainerView)
    }

    @Provides
    @FragmentScoped
    fun provideNavigationManager(navController: NavController) : NavigationManager{
        return NavigationManager(navController)
    }

}