package com.example.storyapp_kotlin.ui.joinStory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.storyapp_kotlin.databinding.FragmentJoinTheStoryPageBinding
import com.example.storyapp_kotlin.models.StoryModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JoinTheStoryPage : Fragment() {

    private lateinit var binding : FragmentJoinTheStoryPageBinding
    private lateinit var currentStoryModel : StoryModel
    private val joinViewModel : joinTheStoryPageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            currentStoryModel = (it.getParcelable("currentStory") as StoryModel?)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJoinTheStoryPageBinding.inflate(inflater,container,false)

        joinStoryButtonClicked()

        return binding.root
    }

    private fun joinStoryButtonClicked(){

        binding.joinStoryButton.setOnClickListener {
            val newStoryContent = binding.userJoinStoryEditText.text.toString()
            joinViewModel.joinTheStory(currentStoryModel.storyId!!,newStoryContent)

            joinViewModel.isJoinStorySuccess.observe(viewLifecycleOwner) { isSuccess ->
                if (isSuccess) {
                    showToast("Story is joined")
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                } else {
                    showToast("Story is not joined")
                }
            }
        }
    }


    //birden fazla fragment için kullandığın durumlar var. Bunu dışarı çıkarabilirsin.
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}