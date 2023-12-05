package com.example.storyapp_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.storyapp_kotlin.ViewModels.AuthViewModel
import com.example.storyapp_kotlin.Views.CompleteTheStory
import com.example.storyapp_kotlin.Views.HomePageFragment
import com.example.storyapp_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val authViewModel : AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val splashScreen = installSplashScreen()

        //checks user sign status
        var userStatusCheck = authViewModel.checkUserSıgnStatus()
        if (userStatusCheck){
            navigateFunc(R.id.action_loginFragment_to_homePageFragment)
            userStatusCheck = !userStatusCheck
        }

        splashScreen.setKeepOnScreenCondition { userStatusCheck }

        setContentView(binding.root)
    }


    fun navigateFunc(action : Int){
        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        val navController = navHost.navController
        navController.navigate(action)
    }

}