package com.example.storyapp_kotlin.ui.story_overview.suprise_me

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.storyapp_kotlin.R
import com.example.storyapp_kotlin.databinding.FragmentSupriseMeDialogBinding
import com.example.storyapp_kotlin.models.StoryModel
import com.example.storyapp_kotlin.ui.story_overview.storyOverviewFragment
import com.example.storyapp_kotlin.ui.story_overview.storyOverviewViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class supriseMeDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding : FragmentSupriseMeDialogBinding
    private val storyOverviewViewModel : storyOverviewViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSupriseMeDialogBinding.inflate(inflater, container, false)

        getSurpriseStory()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupProgressObserver()
        setupCreateStoryButton()
        surpriseStoryListener()

    }


    private fun getSurpriseStory(){
        storyOverviewViewModel.getSurpriseStory()
    }

    private fun setupCreateStoryButton(){
        binding.dialogSupriseMeButton.setOnClickListener {
            getSurpriseStory()
        }
    }

    private fun surpriseStoryListener(){
        storyOverviewViewModel.surpiseStory.observe(viewLifecycleOwner) { story ->
            if (story != null){
                updateStoryUI(story)
            }else{
                println("story is null")
            }
        }
    }

    private fun updateStoryUI(story : StoryModel){
        val storyContent = story.storyContent?.keys?.let { story.storyContent[it.first()] }
        binding.storyContent.text = storyContent ?: "Default Story Content"
        binding.storyTitle.text = story.storyTitle ?: "Default Story Title"

    }


    private fun setupProgressObserver(){
        storyOverviewViewModel.isProgressSuccess.observe(viewLifecycleOwner) { isProgressSuccess ->
            if (isProgressSuccess){
                handleSuccessState()
            }else{
                handleFailureState()
            }
        }
    }

    private fun handleSuccessState(){
        getAnimationSet().start()
        binding.supriseMeProgressBar.visibility = View.GONE
        binding.supriseMeDoneIcon.visibility = View.VISIBLE
        binding.supriseMeInfoTextView.setText("Your book cover is ready!")

    }
    private fun handleFailureState(){
        binding.supriseMeProgressBar.visibility = View.GONE
        binding.supriseMeDoneIcon.visibility = View.VISIBLE
    }

    private fun getAnimationSet(): AnimatorSet {
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(binding.supriseMeDoneIcon, "scaleX", 0f, 1f),
            ObjectAnimator.ofFloat(binding.supriseMeDoneIcon, "scaleY", 0f, 1f),
            ObjectAnimator.ofFloat(binding.supriseMeDoneIcon, "alpha", 0f, 1f)
        )
        animatorSet.duration = 500
        return animatorSet
    }




}