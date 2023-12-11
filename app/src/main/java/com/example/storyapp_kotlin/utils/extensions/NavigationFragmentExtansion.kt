package com.example.storyapp_kotlin.utils.extensions

import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.storyapp_kotlin.R


fun Fragment.createNavigationManager() : NavigationManager {
    return NavigationManager(findNavController())
}