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
import com.example.storyapp_kotlin.ui.common_rv.commonRVadapter
import dagger.hilt.android.AndroidEntryPoint


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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeRv()

    }

    private fun initializeRv() {

        trendingTopRV()
        trendingRV()


    }

    //3 farklı fragmentta bu fonksiyonu kullanıyorsun, daha iyi bir çözüm bul
    private fun createRecyclerView(parentLayout: FrameLayout, category : String){
        val innerLayout = LayoutInflater.from(requireContext()).inflate(R.layout.common_rv_layout, null, false)

        val categoryName = innerLayout.findViewById(R.id.categoryNameTextView) as TextView
        categoryName.text = category

        val recyclerView = innerLayout.findViewById(R.id.common_recyclerView) as RecyclerView
        recyclerView.adapter = commonRVadapter(null, 3)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        parentLayout.addView(innerLayout)
    }

    private fun trendingTopRV(){
        val trendingTop = binding.trendingTop
        createRecyclerView(trendingTop, "Top Trending")
    }

    private fun trendingRV(){
        val secondTrending = binding.trending
        createRecyclerView(secondTrending, "Trending")
    }


}