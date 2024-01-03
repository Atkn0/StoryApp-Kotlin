package com.example.storyapp_kotlin.ui.createStory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.storyapp_kotlin.databinding.FragmentCreateStoryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class createStoryFragment : Fragment() {

    private lateinit var binding: FragmentCreateStoryBinding
    private val createStoryViewModel : createStoryViewModel by viewModels()
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
        lifecycleScope.launch {
            createStoryViewModel.createStory(storyContent)
        }
    }


}