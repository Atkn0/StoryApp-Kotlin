package com.example.storyapp_kotlin.ui.completed_stories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.storyapp_kotlin.R
import com.example.storyapp_kotlin.databinding.FragmentCompletedStoriesBinding
import com.example.storyapp_kotlin.domain.usecase.GetAllUsersUseCase
import com.example.storyapp_kotlin.models.StoryModel
import com.example.storyapp_kotlin.models.UserModel
import com.example.storyapp_kotlin.ui.common_rv.commonRVadapter
import com.example.storyapp_kotlin.utils.RecyclerViewBuilder.RecyclerViewBuilder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class CompletedStoriesFragment @Inject constructor() : Fragment() {

    private lateinit var binding: FragmentCompletedStoriesBinding
    private val completedStoriesViewModel: CompletedStoriesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompletedStoriesBinding.inflate(inflater, container, false)
        completedStoriesViewModel.getStoriesByCollection("CompletedStories")
        getAllUsers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        completedStoriesViewModel.combinedLiveData.observe(viewLifecycleOwner) { (storyList,userList) ->
            if (storyList != null && userList != null) {
                println("storyList: $storyList")
                println("userList: $userList")
                initializeRV(storyList,userList)
            }
        }


    }

    private fun getAllUsers(){
        completedStoriesViewModel.getAllUsers()
    }

    private fun initializeRV(storyList : ArrayList<StoryModel>,userList : ArrayList<UserModel>){
        recentlyCompletedRV(storyList,userList)
        topRatedRV(storyList,userList)
        completedRV(storyList,userList)
    }


    //RecyclerViewBuilder için bir fonksiyon oluşturabilirsin!
    private fun recentlyCompletedRV(storyList: ArrayList<StoryModel>,userList : ArrayList<UserModel>) {
        RecyclerViewBuilder(requireContext())
            .withCategory("Recently Completed")
            .withAdapter(commonRVadapter(storyList,userList))
            .withLayoutManager(LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false))
            .build(binding.recentlyCompleted)
    }

    private fun topRatedRV(storyList: ArrayList<StoryModel>,userList : ArrayList<UserModel>) {
        RecyclerViewBuilder(requireContext())
            .withCategory("Top Rated")
            .withAdapter(commonRVadapter(storyList,userList))
            .withLayoutManager(LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false))
            .build(binding.topRated)
    }

    private fun completedRV(storyList: ArrayList<StoryModel>,userList : ArrayList<UserModel>){
        RecyclerViewBuilder(requireContext())
            .withCategory("Completed")
            .withAdapter(commonRVadapter(storyList,userList))
            .withLayoutManager(LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false))
            .build(binding.completedStories)
    }


}