package com.example.storyapp_kotlin.ui.common_rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.storyapp_kotlin.databinding.CommonRvItemLayoutBinding
import com.example.storyapp_kotlin.databinding.CommonRvLayoutBinding
import com.example.storyapp_kotlin.databinding.FragmentTrendingBinding
import com.example.storyapp_kotlin.models.StoryModel

class commonRVadapter(val storyList: ArrayList<StoryModel>?,val number: Int) : RecyclerView.Adapter<commonRVadapter.ViewHolder>() {
    class ViewHolder (val binding: CommonRvItemLayoutBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CommonRvItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return number
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.commonRvItem
    }
}