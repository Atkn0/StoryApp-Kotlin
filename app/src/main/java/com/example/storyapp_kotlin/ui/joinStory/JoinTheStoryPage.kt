package com.example.storyapp_kotlin.ui.joinStory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.storyapp_kotlin.R
import com.example.storyapp_kotlin.databinding.FragmentJoinTheStoryPageBinding

class JoinTheStoryPage : Fragment() {

    private lateinit var binding : FragmentJoinTheStoryPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJoinTheStoryPageBinding.inflate(inflater,container,false)
        return binding.root
    }


}