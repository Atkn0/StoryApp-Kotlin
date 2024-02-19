package com.example.storyapp_kotlin.ui.profile.profile_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.storyapp_kotlin.R
import com.example.storyapp_kotlin.databinding.FragmentProfileDetailsBinding


class ProfileDetailsFragment : Fragment() {

    private lateinit var binding : FragmentProfileDetailsBinding
    private val args : ProfileDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileDetailsBinding.inflate(inflater, container, false)


        val user = args.currentUser
        println(user)

        return binding.root
    }


}