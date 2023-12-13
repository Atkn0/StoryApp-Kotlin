package com.example.storyapp_kotlin.ui.storyBottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.storyapp_kotlin.databinding.StoryBottomSheetFragmentBinding
import com.example.storyapp_kotlin.di.NavigationManager.NavigationManager
import com.example.storyapp_kotlin.models.StoryModel
import com.example.storyapp_kotlin.ui.home.HomePageFragment
import com.example.storyapp_kotlin.ui.home.HomePageFragmentDirections
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class storyBottomSheetFragment(val storyModel: StoryModel) : BottomSheetDialogFragment() {

    private lateinit var binding : StoryBottomSheetFragmentBinding

    @Inject
    lateinit var navigationManager: NavigationManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = StoryBottomSheetFragmentBinding.inflate(inflater,container,false)


        println("Story Model : $storyModel")

        with(binding){
            joinTheStoryButton.setOnClickListener {
                val direction = HomePageFragmentDirections.actionHomePageFragmentToJoinTheStoryPage()
                navigationManager.navigateTo(direction)
                dismiss()
            }
        }


        return binding.root
    }
}