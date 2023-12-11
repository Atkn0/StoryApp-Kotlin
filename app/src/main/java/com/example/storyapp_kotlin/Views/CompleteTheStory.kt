package com.example.storyapp_kotlin.Views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.storyapp_kotlin.databinding.CompleteStoryLayoutBinding
import com.example.storyapp_kotlin.databinding.FragmentCompleteTheStoryBinding


class CompleteTheStory : Fragment() {

    private lateinit var binding: FragmentCompleteTheStoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCompleteTheStoryBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


}