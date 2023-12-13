package com.example.storyapp_kotlin.ui.completeTheStory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.storyapp_kotlin.databinding.StoryBottomSheetFragmentBinding
import com.example.storyapp_kotlin.models.StoryModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class storyBottomSheetFragment(val storyModel: StoryModel) : BottomSheetDialogFragment() {

    private lateinit var binding : StoryBottomSheetFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = StoryBottomSheetFragmentBinding.inflate(inflater,container,false)


        println("Story Model : $storyModel")


        return binding.root
    }
}