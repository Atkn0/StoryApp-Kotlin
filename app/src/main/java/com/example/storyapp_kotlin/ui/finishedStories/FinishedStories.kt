package com.example.storyapp_kotlin.ui.finishedStories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.storyapp_kotlin.R
import com.example.storyapp_kotlin.databinding.FragmentFinishedStoriesBinding


class FinishedStories : Fragment() {

    private lateinit var binding: FragmentFinishedStoriesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFinishedStoriesBinding.inflate(inflater, container, false)
        return binding.root
    }


}