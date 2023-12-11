package com.example.storyapp_kotlin.utils.extensions

import androidx.navigation.NavController
import androidx.navigation.NavDirections

class NavigationManager(private val navController: NavController) {

    fun navigateTo(destination: NavDirections) {
        navController.navigate(destination)
    }

}