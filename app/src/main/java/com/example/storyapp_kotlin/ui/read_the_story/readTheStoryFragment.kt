package com.example.storyapp_kotlin.ui.read_the_story

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.storyapp_kotlin.R
import com.example.storyapp_kotlin.databinding.FragmentReadTheStoryBinding
import com.example.storyapp_kotlin.models.StoryModel
import com.example.storyapp_kotlin.ui.read_the_story.adapters.readViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class readTheStoryFragment : Fragment() {

    private lateinit var binding: FragmentReadTheStoryBinding
    private lateinit var readViewPager: ViewPager2
    private lateinit var currentStoryModel: StoryModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            currentStoryModel = (it.getParcelable("currentStory") as StoryModel?)!!
        }

        println(currentStoryModel.storyId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentReadTheStoryBinding.inflate(inflater,container,false)

        readViewPager = binding.readViewPager
        readViewPager.adapter = readViewPagerAdapter(requireActivity().supportFragmentManager,lifecycle,currentStoryModel)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}