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
import com.example.storyapp_kotlin.databinding.FragmentLoginBinding


class loginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val authViewModel : AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            loginButton.setOnClickListener {
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()
                authViewModel.userLogin(email, password)
            }
        }

        userSignCheckStatus()

        binding.dontHaveAccountTextView.setOnClickListener {
            navigateFromLoginPage(R.id.action_loginFragment_to_signupFragment)
        }
    }


    fun navigateFromLoginPage(action : Int){
        val navHost =
            activity?.supportFragmentManager?.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        val navController = navHost.navController
        navController.navigate(action)
    }

    fun userSignCheckStatus(){
        authViewModel.isUserSignedIn.observe(viewLifecycleOwner){ task->
            if (task) {
                navigateFromLoginPage(R.id.action_loginFragment_to_allStoriesHomePageFragment)
            } else {
                println("User is not signed in")
            }
        }
    }

}