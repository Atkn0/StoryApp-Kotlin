package com.example.storyapp_kotlin.ui.completeTheStory.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.storyapp_kotlin.databinding.ContributionsLayoutBinding
import com.example.storyapp_kotlin.models.StoryModel
import com.example.storyapp_kotlin.models.UserModel

class ContributionsRVAdapter(val storyList : ArrayList<StoryModel>) : RecyclerView.Adapter<ContributionsRVAdapter.ContributionsViewHolder>() {
    class ContributionsViewHolder(val binding: ContributionsLayoutBinding)  : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContributionsViewHolder {
        val binding = ContributionsLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ContributionsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun onBindViewHolder(holder: ContributionsViewHolder, position: Int) {
        holder.binding.contributionsCardView
    }

    fun updateContributionsList(newContributionsList : ArrayList<StoryModel>){
        storyList.clear()
        storyList.addAll(newContributionsList)
        notifyDataSetChanged()
    }

}
