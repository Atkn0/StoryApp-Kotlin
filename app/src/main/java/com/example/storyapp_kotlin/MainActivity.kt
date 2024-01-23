package com.example.storyapp_kotlin

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
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
        val splashScreen = installSplashScreen()



        //checks user sign status beginning of the app
        var userStatusCheck = authViewModel.checkUserSignStatus()
        if (userStatusCheck){
            navigateFunc(R.id.action_loginFragment_to_homePageFragment)
            userStatusCheck = !userStatusCheck
        }

        splashScreen.setKeepOnScreenCondition { userStatusCheck }

        authViewModel.isUserSignedIn.observe(this) {
            if (it) {
                navigateFunc(R.id.action_loginFragment_to_homePageFragment)
            }else{
                println("Kullanıcı çıkış yaptı!")
            }
        }

        val bottomNavView = binding.bottomNavigationView
        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHost.navController
        bottomNavView.setupWithNavController(navController)


        setContentView(binding.root)
    }

    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {
        return super.onCreateView(parent, name, context, attrs)


    }



    fun navigateFunc(action : Int){
        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHost.navController
        navController.navigate(action)
    }

}