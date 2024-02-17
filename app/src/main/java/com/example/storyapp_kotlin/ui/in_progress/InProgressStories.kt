package com.example.storyapp_kotlin.ui.in_progress

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.storyapp_kotlin.R
import com.example.storyapp_kotlin.databinding.FragmentInProgressStoriesBinding
import com.example.storyapp_kotlin.models.StoryModel
import com.example.storyapp_kotlin.models.UserModel
import com.example.storyapp_kotlin.utils.common_rv.commonRVadapter
import com.example.storyapp_kotlin.utils.RecyclerViewBuilder.RecyclerViewBuilder
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class InProgressStories : Fragment() {

    private lateinit var binding: FragmentInProgressStoriesBinding
    private val inProgressViewModel : InProgressViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding  = FragmentInProgressStoriesBinding.inflate(inflater, container, false)
        inProgressViewModel.getStoriesByCollection("InProgressStories")
        getAllUsers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inProgressViewModel.combinedLiveData.observe(viewLifecycleOwner) { (storyList,userList) ->
            if (storyList != null && userList != null) {
                println("storyList: $storyList")
                println("userList: $userList")
                initializeRV(storyList,userList)
            }
        }

    }


    private fun getAllUsers(){
        inProgressViewModel.getAllUsers()
    }

    private fun initializeRV(storyList : ArrayList<StoryModel>,userList:ArrayList<UserModel>){
        newReleaseRV(storyList,userList)
        inProgressRV(storyList,userList)
    }

    private fun newReleaseRV(storyList : ArrayList<StoryModel>,userList:ArrayList<UserModel>){

        val sortedStoryList = sortedStoryList(storyList)

        RecyclerViewBuilder(requireContext())
            .withCategory("New Release")
            .withAdapter(commonRVadapter(sortedStoryList,userList))
            .withLayoutManager(LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false))
            .build(binding.newRelease)
    }

    private fun inProgressRV(storyList: ArrayList<StoryModel>,userList:ArrayList<UserModel>){
        RecyclerViewBuilder(requireContext())
            .withCategory("In Progress")
            .withAdapter(commonRVadapter(storyList,userList))
            .withLayoutManager(LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false))
            .build(binding.inProgressStories)
    }

    private fun sortedStoryList(storyList: ArrayList<StoryModel>): ArrayList<StoryModel> {
        return storyList.sortedBy { it.createdDate }.reversed() as ArrayList<StoryModel>
    }

}