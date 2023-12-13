package com.example.storyapp_kotlin.ui.completeTheStory.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.storyapp_kotlin.models.StoryModel
import com.example.storyapp_kotlin.databinding.CompleteStoryLayoutBinding
import com.example.storyapp_kotlin.databinding.ContributionsLayoutBinding
import com.example.storyapp_kotlin.models.UserModel

class CompleteStoryRVAdapter(private var storyList : ArrayList<StoryModel>,private val contributionList : ArrayList<UserModel>) : RecyclerView.Adapter<CompleteStoryRVAdapter.CompleteStoryViewHolder>() {

    var onStoryClicked : (StoryModel) -> Unit = {}

    class CompleteStoryViewHolder(val binding: CompleteStoryLayoutBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompleteStoryViewHolder {
        val binding = CompleteStoryLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CompleteStoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return storyList.size
    }

    override fun onBindViewHolder(holder: CompleteStoryViewHolder, position: Int) {

        with(holder.binding){
            completeStoryContent.text = storyList[position].storyContent
            completeStoryNumberOfLikes.text = storyList[position].numberOfLikes.toString()
            completeStoryNumberOfReaders.text = storyList[position].numberOfReader.toString()

            // Contributions Recycler View
            contrubutorsRecyclerView.adapter = ContributionsRVAdapter(storyList)
            contrubutorsRecyclerView.layoutManager = LinearLayoutManager(contrubutorsRecyclerView.context,LinearLayoutManager.HORIZONTAL,false)
            contrubutorsRecyclerView.setHasFixedSize(true)

            completeStoryCardView.setOnClickListener {
                onStoryClicked(storyList[position])
            }

        }

    }


    fun setData(newStoryList : ArrayList<StoryModel>){
        storyList = newStoryList
        notifyDataSetChanged()
    }
}