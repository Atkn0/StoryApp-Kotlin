package com.example.storyapp_kotlin.di.NavigationManager

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import dagger.Provides
import javax.inject.Inject

class NavigationManager @Inject constructor(private val navController: NavController) {
    fun navigateTo(directions: NavDirections) {
        navController.navigate(directions)
    }
}