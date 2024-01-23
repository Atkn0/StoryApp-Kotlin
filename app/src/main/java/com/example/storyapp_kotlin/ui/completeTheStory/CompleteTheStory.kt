package com.example.storyapp_kotlin.ui.completeTheStory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storyapp_kotlin.ui.completeTheStory.adapter.CompleteStoryRVAdapter
import com.example.storyapp_kotlin.databinding.FragmentCompleteTheStoryBinding
import com.example.storyapp_kotlin.ui.storyBottomSheet.storyBottomSheetFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompleteTheStory : Fragment() {

    private lateinit var binding: FragmentCompleteTheStoryBinding
    private val completeTheStoryViewModel : completeTheStoryViewModel by viewModels()
    private lateinit var completeStoryRVAdapter : CompleteStoryRVAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        println("CompleteTheStory: Yeniden Firebase'den veri çekiliyor")
        completeTheStoryViewModel.getAllCompleteStories()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompleteTheStoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeAdapter()
        observers()
        clickedListeners()

    }

    private fun observers(){
        completeTheStoryViewModel.storyModelLiveData.observe(viewLifecycleOwner) { storyModelList ->
            if (storyModelList != null) {
                completeStoryRVAdapter.setData(storyModelList)
            }
        }
    }

    private fun clickedListeners(){
        completeStoryRVAdapter.onStoryClicked = { storyModel ->
            val storyBottomSheet = storyBottomSheetFragment(storyModel)
            storyBottomSheet.show(childFragmentManager, "storyBottomSheet")
        }
    }

    fun initializeAdapter(){
        completeStoryRVAdapter = CompleteStoryRVAdapter(arrayListOf(), arrayListOf())
        binding.recyclerViewCompleteTheStory.adapter = completeStoryRVAdapter
        binding.recyclerViewCompleteTheStory.layoutManager = LinearLayoutManager(requireContext())
    }

}