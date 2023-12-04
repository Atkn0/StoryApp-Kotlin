package com.example.storyapp_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.storyapp_kotlin.ViewModels.AuthViewModel
import com.example.storyapp_kotlin.Views.allStoriesHomePageFragment
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
            changeFragment(allStoriesHomePageFragment())
            userStatusCheck = !userStatusCheck
        }

        splashScreen.setKeepOnScreenCondition { userStatusCheck }

        setContentView(binding.root)
    }

    fun changeFragment(fragment: allStoriesHomePageFragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView2, fragment)
            commit()
        }
    }

}