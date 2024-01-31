package com.example.storyapp_kotlin.ui.story_overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.storyapp_kotlin.R
import com.example.storyapp_kotlin.databinding.FragmentStoryOverviewBinding
import com.example.storyapp_kotlin.models.SafeArgsObject
import com.example.storyapp_kotlin.ui.story_overview.suprise_me.supriseMeDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class storyOverviewFragment : Fragment() {

    lateinit var binding : FragmentStoryOverviewBinding
    private val storyOverviewViewModel : storyOverviewViewModel by activityViewModels()
    val args : storyOverviewFragmentArgs by navArgs()
    lateinit var storyContent : String
    lateinit var storyTitle : String

    private var buttonX : Float = 0.0f
    private var buttonY : Float = 0.0f



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStoryOverviewBinding.inflate(inflater, container, false)

        getButtonLocation()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        storyContent = args.currentStory.storyContent
        storyTitle = args.currentStory.storyTitle

        lifecycleScope.launch {
            storyOverviewViewModel.makeApiCallImage(storyContent)
        }

        setupObservers()
        setupCreateStoryButton()
        setupSupriseMeButton()
        observeStoryImageUrl()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        storyOverviewViewModel.clearData()
    }

    private fun setupObservers() {
        storyOverviewViewModel.isProgressSuccess.observe(viewLifecycleOwner) {
            binding.storyImageProgressBar.visibility = if (it) View.GONE else View.VISIBLE
        }

        storyOverviewViewModel.isAddStorySuccess.observe(viewLifecycleOwner) {isSuccess->
            if (isSuccess == true){
                Toast.makeText(requireContext(), "Story added successfully!", Toast.LENGTH_SHORT).show()
                view?.postDelayed({
                    findNavController().popBackStack()
                }, 1500)
            }else{
                Toast.makeText(requireContext(), "Story added failed!", Toast.LENGTH_LONG).show()

            }
        }

    }
    private fun setupCreateStoryButton() {
        binding.createStoryButton.setOnClickListener {
            lifecycleScope.launch {
                storyOverviewViewModel.addCreatedStoryToFirestore(storyTitle = storyTitle,storyContent)
            }
        }
    }
    private fun setupSupriseMeButton() {
        binding.supriseMeButton.setOnClickListener {
            openSupriseMeDialog()
        }
    }
    private fun observeStoryImageUrl() {
        storyOverviewViewModel.storyImageUrl.observe(viewLifecycleOwner) { imageUrl ->
            Glide.with(requireContext()).load(imageUrl).into(binding.storyImageView)
        }
    }
    private fun openSupriseMeDialog(){
        val dialog = supriseMeDialogFragment()
        val bundle = Bundle()
        buttonX = binding.supriseMeButton.x
        buttonY = binding.supriseMeButton.y
        bundle.putFloat("buttonX", buttonX)
        bundle.putFloat("buttonY", buttonY)
        dialog.arguments = bundle
        dialog.show(parentFragmentManager, "bundle")
    }
    private fun getButtonLocation(){
        val location = IntArray(2)
        binding.supriseMeButton.getLocationOnScreen(location)
        buttonX = location[0].toFloat()
        buttonY = location[1].toFloat()
    }


}