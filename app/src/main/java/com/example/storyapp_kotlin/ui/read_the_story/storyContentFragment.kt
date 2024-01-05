package com.example.storyapp_kotlin.ui.read_the_story

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.storyapp_kotlin.R
import com.example.storyapp_kotlin.databinding.FragmentStoryContentBinding


class storyContentFragment (val storyContent : String): Fragment() {

    private lateinit var binding : FragmentStoryContentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStoryContentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.storyContentTextView.text = storyContent

    }


}