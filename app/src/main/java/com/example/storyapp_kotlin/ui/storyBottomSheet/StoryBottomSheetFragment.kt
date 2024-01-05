package com.example.storyapp_kotlin.ui.storyBottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.storyapp_kotlin.databinding.StoryBottomSheetFragmentBinding
import com.example.storyapp_kotlin.di.NavigationManager.NavigationManager
import com.example.storyapp_kotlin.models.StoryModel
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

        initializeUI()
        buttonClickedListener()


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun initializeUI(){
        binding.numberOfLikesTextView .text = storyModel.numberOfLikes.toString()
        binding.numberOfReadsTextView.text = storyModel.numberOfReader.toString()
        binding.storyBottomSheetStoryContent.text = storyModel.storyContent?.get("storyContent")
    }
    private fun buttonClickedListener(){
        println("ButtonClickedListener story id : ${storyModel.storyId}")
        binding.joinTheStoryButton.setOnClickListener {
            val direction = HomePageFragmentDirections.actionHomePageFragmentToJoinTheStoryPage(storyModel)
            navigationManager.navigateTo(direction)
            dismiss()
        }

        binding.readTheStoryButton.setOnClickListener {
            val direction = HomePageFragmentDirections.actionHomePageFragmentToReadTheStoryFragment(storyModel)
            navigationManager.navigateTo(direction)
            dismiss()
        }

    }


}