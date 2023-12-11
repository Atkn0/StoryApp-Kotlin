package com.example.storyapp_kotlin.NavigationHelper

import android.content.Context
import androidx.navigation.NavController
import dagger.Component
import dagger.Module
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationHelper @Inject constructor(private val navController: NavController){

    fun navigateTo(action : Int){
        navController.navigate(action)
    }


}