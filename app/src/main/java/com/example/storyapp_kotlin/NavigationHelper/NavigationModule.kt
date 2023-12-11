package com.example.storyapp_kotlin.NavigationHelper

import android.app.Activity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.storyapp_kotlin.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(FragmentComponent::class, ActivityComponent::class)
object NavigationModule {

    @Provides
    fun provideNavController(activity: Activity): NavController {
        return Navigation.findNavController(activity, R.id.fragmentContainerView2)
    }

    @Provides
    fun provideNavigationHelper(navController: NavController): NavigationHelper {
        return NavigationHelper(navController)
    }

}