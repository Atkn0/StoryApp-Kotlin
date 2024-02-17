package com.example.storyapp_kotlin.ui.trending

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Layout
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
import com.example.storyapp_kotlin.databinding.FragmentTrendingBinding
import com.example.storyapp_kotlin.models.StoryModel
import com.example.storyapp_kotlin.models.UserModel
import com.example.storyapp_kotlin.utils.common_rv.commonRVadapter
import com.example.storyapp_kotlin.utils.RecyclerViewBuilder.RecyclerViewBuilder
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList


@AndroidEntryPoint
class TrendingFragment : Fragment() {

    private lateinit var binding: FragmentTrendingBinding
    private lateinit var commonRVAdapter: commonRVadapter
    private val trendingViewModel : TrendingViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrendingBinding.inflate(inflater, container, false)
        trendingViewModel.getStoriesByCollection("TrendingStories")
        getAllUsers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        trendingViewModel.combinedLiveData.observe(viewLifecycleOwner) { (storyList,userList) ->
            if (storyList != null && userList != null) {
                initializeRv(storyList,userList)
            }
        }


    }

    private fun getAllUsers(){
        trendingViewModel. getAllUsers()
    }

    private fun initializeRv(storyList : ArrayList<StoryModel>,userList: ArrayList<UserModel>) {
        trendingTopRV(storyList,userList)
        trendingRV(storyList,userList)
    }

    private fun trendingTopRV(storyList : ArrayList<StoryModel>,userList: ArrayList<UserModel>){

        val sortedStoryList = sortStoriesByTrendingScore(storyList)

        createRV(sortedStoryList,userList,"TrendingTop",binding.trendingTop)
    }

    private fun trendingRV(storyList : ArrayList<StoryModel>,userList: ArrayList<UserModel>){
        createRV(storyList,userList,"Trending",binding.trending)
    }

    private fun createRV(
        storyList: ArrayList<StoryModel>,
        userList: ArrayList<UserModel>,
        category: String,
        layout: FrameLayout
    ) {
        RecyclerViewBuilder(requireContext())
            .withCategory(category)
            .withAdapter(commonRVadapter(storyList, userList))
            .withLayoutManager(LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false))
            .build(layout)
    }


    private fun sortStoriesByTrendingScore(storyList: ArrayList<StoryModel>): ArrayList<StoryModel> {
        val sortedList = storyList.sortedByDescending { getTrendingScore(it) }
        return ArrayList(sortedList)
    }


    private fun getTrendingScore(story: StoryModel): Double {
        val likesRatio = (story.numberOfLikes ?: 0) / (story.numberOfReader ?: 1).toDouble()
        val currentTimeMillis = System.currentTimeMillis()
        val createdAtMillis = story.createdDate?.toDate()?.time ?: currentTimeMillis
        val elapsedTimeDays = (currentTimeMillis - createdAtMillis) / (24 * 60 * 60 * 1000) // Geçen zamanı gün cinsinden hesapla

        // Trend skoru hesaplama formülü
        val trendScore = likesRatio / (1 + elapsedTimeDays) // 1 gün sonrası için düşüş başlar
        return trendScore
    }


}