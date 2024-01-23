package com.example.storyapp_kotlin.ui.trending

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        createFirstTrendingRV()
        createSecondTrendingRV()


    }

    private fun createFirstTrendingRV(){
        val trending_1 = binding.trending1

        val innerLayout = LayoutInflater.from(requireContext()).inflate(R.layout.common_rv_layout, null, false)

        val firstTrendingRV = innerLayout.findViewById(R.id.common_recyclerView) as RecyclerView
        firstTrendingRV.adapter = commonRVadapter(null,6)
        firstTrendingRV.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        trending_1.addView(innerLayout)

    }

    private fun createSecondTrendingRV(){
        val trending_2 = binding.trending2

        val innerLayout = LayoutInflater.from(requireContext()).inflate(R.layout.common_rv_layout, null, false)

        val secondTrendingRV = innerLayout.findViewById(R.id.common_recyclerView) as RecyclerView
        secondTrendingRV.adapter = commonRVadapter(null,2)
        secondTrendingRV.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        trending_2.addView(innerLayout)

    }


}