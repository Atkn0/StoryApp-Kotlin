package com.example.storyapp_kotlin.ui.home.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.storyapp_kotlin.databinding.CategoryButtonLayoutBinding

class categoryRVAdapter() : RecyclerView.Adapter<categoryRVAdapter.categoryViewHolder>(){

    private val categoryList = listOf("Trending","Completed Stories","In Progress Stories")
    private var selectedPosition = 0

    var onCategoryClick: ((String) -> Unit)? = null

    class categoryViewHolder(val binding:CategoryButtonLayoutBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): categoryViewHolder {
        val binding = CategoryButtonLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return categoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: categoryViewHolder, position: Int) {

        with(holder.binding){
            categoryName.text = categoryList[position]

            categoryButtonIcon.setImageResource(
                when(position){
                    0 -> com.example.storyapp_kotlin.R.drawable.uil_fire
                    1 -> com.example.storyapp_kotlin.R.drawable.uil_fire
                    else -> com.example.storyapp_kotlin.R.drawable.uil_fire

                }
            )

            categoryName.setTextColor(if (position == selectedPosition) Color.parseColor("#313333") else Color.parseColor("#EAF4F4"))
            categoryButtonIcon.setColorFilter(if (position == selectedPosition) Color.parseColor("#313333") else Color.parseColor("#EAF4F4"))

            categoryButton.setOnClickListener {
                notifyItemChanged(selectedPosition)
                selectedPosition = holder.adapterPosition
                notifyItemChanged(selectedPosition)

                onCategoryClick?.invoke(categoryList[position])

            }

            val selectedColor = if (position == selectedPosition) Color.parseColor("#CDE7BE") else Color.parseColor("#00FFFFFF")
            categoryButton.setCardBackgroundColor(selectedColor)
        }









    }

}