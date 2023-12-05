package com.example.storyapp_kotlin.Views

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.storyapp_kotlin.Models.StoryModel
import com.example.storyapp_kotlin.R
import com.example.storyapp_kotlin.ViewModels.FirestoreViewModel
import com.example.storyapp_kotlin.databinding.FragmentCreateStoryBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class createStoryFragment : Fragment() {

    private lateinit var binding: FragmentCreateStoryBinding
    private val firestoreViewModel : FirestoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateStoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.createStorybutton.setOnClickListener {
            createStory()
        }

    }

    private fun createStory() {
        val storyContent = binding.editTextTextMultiLine.text.toString()
        GlobalScope.launch {
            firestoreViewModel.createStory(storyContent)
        }
    }


}