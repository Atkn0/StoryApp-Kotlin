package com.example.storyapp_kotlin.ui.joinStory

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.example.storyapp_kotlin.R
import com.example.storyapp_kotlin.databinding.FragmentJoinTheStoryPageBinding
import com.example.storyapp_kotlin.models.StoryModel

class JoinTheStoryPage : Fragment() {

    private lateinit var binding : FragmentJoinTheStoryPageBinding
    private lateinit var currentStoryModel : StoryModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            currentStoryModel = (it.getParcelable("currentStory") as StoryModel?)!!
            println("joinStoryPage currentStoryModel : $currentStoryModel")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJoinTheStoryPageBinding.inflate(inflater,container,false)

        binding.textEditText.setText(currentStoryModel.storyContent)
        //binding.textEditText.text = currentStoryModel.storyContent as Editable?
        return binding.root
    }


}