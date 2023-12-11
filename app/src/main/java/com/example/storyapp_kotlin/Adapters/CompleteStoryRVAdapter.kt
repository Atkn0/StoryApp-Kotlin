package com.example.storyapp_kotlin.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.storyapp_kotlin.Models.StoryModel
import com.example.storyapp_kotlin.databinding.CompleteStoryLayoutBinding
import com.example.storyapp_kotlin.databinding.FragmentCompleteTheStoryBinding

class CompleteStoryRVAdapter(var storyList : ArrayList<StoryModel>) : RecyclerView.Adapter<CompleteStoryRVAdapter.CompleteStoryViewHolder>() {

    class CompleteStoryViewHolder(val binding: CompleteStoryLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompleteStoryViewHolder {
        val binding = CompleteStoryLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CompleteStoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return storyList.size
    }

    override fun onBindViewHolder(holder: CompleteStoryViewHolder, position: Int) {
        holder.binding.completeStoryContent.text = storyList[position].storyContent
        holder.binding.completeStoryNumberOfLikes.text = storyList[position].numberOfLikes.toString()
        holder.binding.completeStoryNumberOfReaders.text = storyList[position].numberOfReader.toString()

    }


    fun setData(newStoryList : ArrayList<StoryModel>){
        storyList = newStoryList
        notifyDataSetChanged()
    }
}