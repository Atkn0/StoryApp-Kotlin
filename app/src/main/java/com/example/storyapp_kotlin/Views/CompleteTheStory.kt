package com.example.storyapp_kotlin.Views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storyapp_kotlin.Adapters.CompleteStoryRVAdapter
import com.example.storyapp_kotlin.Models.StoryModel
import com.example.storyapp_kotlin.ViewModels.completeTheStoryViewModel
import com.example.storyapp_kotlin.databinding.FragmentCompleteTheStoryBinding


class CompleteTheStory : Fragment() {

    private lateinit var binding: FragmentCompleteTheStoryBinding
    private val completeTheStoryViewModel : completeTheStoryViewModel by viewModels()
    private lateinit var completeStoryRVAdapter : CompleteStoryRVAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        completeTheStoryViewModel.getAllCompleteStories()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCompleteTheStoryBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        completeStoryRVAdapter = CompleteStoryRVAdapter(arrayListOf())

        completeTheStoryViewModel.storyModelLiveData.observe(viewLifecycleOwner) { storyModelList ->
            completeStoryRVAdapter.setData(storyModelList)
        }

        binding.recyclerViewCompleteTheStory.adapter = completeStoryRVAdapter
        binding.recyclerViewCompleteTheStory.layoutManager = LinearLayoutManager(requireContext())

    }


}