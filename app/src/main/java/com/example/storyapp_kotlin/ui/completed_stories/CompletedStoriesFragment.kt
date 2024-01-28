package com.example.storyapp_kotlin.ui.completed_stories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.storyapp_kotlin.R
import com.example.storyapp_kotlin.databinding.FragmentCompletedStoriesBinding
import com.example.storyapp_kotlin.ui.common_rv.commonRVadapter


class CompletedStoriesFragment : Fragment() {

    private lateinit var binding: FragmentCompletedStoriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompletedStoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeRV()

    }

    private fun initializeRV(){
        recentlyCompletedRV()
        topRatedRV()
        completedRV()
    }

    //3 farklı fragmentta bu fonksiyonu kullanıyorsun, daha iyi bir çözüm bul
    private fun createRecyclerView(parentLayout: FrameLayout, category:String) {
        val innerLayout = LayoutInflater.from(requireContext()).inflate(R.layout.common_rv_layout, null, false)

        val categoryName = innerLayout.findViewById(R.id.categoryNameTextView) as TextView
        categoryName.text = category

        val recyclerView = innerLayout.findViewById(R.id.common_recyclerView) as RecyclerView
        recyclerView.adapter = commonRVadapter(null, 6)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        parentLayout.addView(innerLayout)
    }

    private fun recentlyCompletedRV() {
        val recentlyCompleted = binding.recentlyCompleted
        createRecyclerView(recentlyCompleted, "Recently Completed")
    }

    private fun topRatedRV() {
        val topRated = binding.topRated
        createRecyclerView(topRated, "Top Rated")
    }

    private fun completedRV(){
        val completed = binding.completedStories
        createRecyclerView(completed, "Completed Stories")
    }


}