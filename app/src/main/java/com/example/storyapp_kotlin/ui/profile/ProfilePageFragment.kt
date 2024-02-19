package com.example.storyapp_kotlin.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.storyapp_kotlin.ui.login.AuthViewModel
import com.example.storyapp_kotlin.databinding.FragmentProfilePageBinding
import com.example.storyapp_kotlin.di.NavigationManager.NavigationManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class profilePageFragment  : Fragment() {

    private lateinit var binding: FragmentProfilePageBinding

    private val profilePageViewModel : ProfilePageViewModel by viewModels()
    private val authViewModel : AuthViewModel by viewModels()

    @Inject
    lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfilePageBinding.inflate(inflater, container, false)

        getCurrentUserModel()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        backHomeNavigation()
        logoutListener()
        userDataListener()
        profileDetailsButtonListener()

    }




    private fun backHomeNavigation() {
        binding.profileBackHomeLinearLayout.setOnClickListener {
            val action = profilePageFragmentDirections.actionProfilePageFragmentToHomePageFragment()
            navigationManager.navigateTo(action)
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            val action = profilePageFragmentDirections.actionProfilePageFragmentToHomePageFragment()
            navigationManager.navigateTo(action)
        }
    }

    private fun logoutListener(){
        binding.logoutConstraintLayout.setOnClickListener {
            authViewModel.logout()
            val action = profilePageFragmentDirections.actionProfilePageFragmentToLoginFragment()
            navigationManager.navigateTo(action)
        }
    }

    private fun getCurrentUserModel(){
        profilePageViewModel.getUserById()
    }

    private fun userDataListener(){
        profilePageViewModel.user.observe(viewLifecycleOwner) { user ->
            binding.emailTextView.text = user?.email
        }
    }

    private fun profileDetailsButtonListener(){
        binding.profileDetailsConstraintLayout.setOnClickListener {
            val user = profilePageViewModel.user.value
            if(user != null){
                val action = profilePageFragmentDirections.actionProfilePageFragmentToProfileDetailsFragment(user)
                navigationManager.navigateTo(action)
            }else{
                lifecycleScope.launch {
                    getCurrentUserModel()
                }
                profileDetailsButtonListener()
            }
        }
    }

}