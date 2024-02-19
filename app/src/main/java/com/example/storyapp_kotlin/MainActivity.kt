package com.example.storyapp_kotlin

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.window.OnBackInvokedDispatcher
import android.window.SplashScreen
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.storyapp_kotlin.ui.login.AuthViewModel
import com.example.storyapp_kotlin.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val authViewModel : AuthViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        authListener()
        setupNavController()
        androidSystemBackButton()

        setContentView(binding.root)

    }






    fun navigateFunc(action : Int){
        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHost.navController
        navController.navigate(action)
    }

    private fun authListener(){

        val splashScreen = installSplashScreen()

        authViewModel.checkUserSignStatus().let { userSignedIn ->
            if (userSignedIn) {
                navigateFunc(R.id.action_loginFragment_to_homePageFragment)
            } else {
                splashScreen.setKeepOnScreenCondition { false }
                authViewModel.isUserSignedIn.observe(this) { signInStatus ->
                    if (signInStatus) {
                        navigateFunc(R.id.action_loginFragment_to_homePageFragment)
                    } else {
                        println("Kullanıcı çıkış yaptı!")
                    }
                }
            }
        }

    }
    private fun setupNavController(){
        // Bottom navigation view için NavController'ı ayarlama
        val bottomNavView = binding.bottomNavigationView
        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHost.navController
        bottomNavView.setupWithNavController(navController)
    }

    private fun androidSystemBackButton(){
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {}
        }
        onBackPressedDispatcher.addCallback(this, callback)
    }

}