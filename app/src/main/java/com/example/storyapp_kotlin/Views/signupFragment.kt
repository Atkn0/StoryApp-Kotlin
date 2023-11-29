package com.example.storyapp_kotlin.Views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.storyapp_kotlin.R
import com.example.storyapp_kotlin.ViewModels.AuthViewModel
import com.example.storyapp_kotlin.ViewModels.FirestoreViewModel
import com.example.storyapp_kotlin.databinding.FragmentSignupBinding


class signupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private val authViewModel : AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSignupBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            authViewModel.createUser(email, password)
        }
        checkUserSignInStatus()
    }


    fun navigateFromRegisterPage(action:Int){
        val navHost =
            activity?.supportFragmentManager?.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        val navController = navHost.navController
        navController.navigate(action)
    }

    fun checkUserSignInStatus(){
        authViewModel.isUserSignedIn.observe(viewLifecycleOwner) { value ->
            if (value) {
                navigateFromRegisterPage(R.id.action_signupFragment_to_allStoriesHomePageFragment)
            }
        }
    }

}