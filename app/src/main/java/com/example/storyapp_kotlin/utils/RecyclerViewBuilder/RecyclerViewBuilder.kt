package com.example.storyapp_kotlin.utils.RecyclerViewBuilder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.storyapp_kotlin.R

class RecyclerViewBuilder(private val context : Context) {
    private var category: String? = null
    private var adapter: RecyclerView.Adapter<*>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null

    fun withCategory(category: String): RecyclerViewBuilder {
        this.category = category
        return this
    }
    fun withAdapter(adapter: RecyclerView.Adapter<*>): RecyclerViewBuilder {
        this.adapter = adapter
        return this
    }
    fun withLayoutManager(layoutManager: RecyclerView.LayoutManager): RecyclerViewBuilder {
        this.layoutManager = layoutManager
        return this
    }
    fun build(parentLayout: FrameLayout): View {
        val innerLayout = LayoutInflater.from(context).inflate(R.layout.common_rv_layout, null, false)

        val categoryName = innerLayout.findViewById<TextView>(R.id.categoryNameTextView)
        categoryName.text = category

        val recyclerView = innerLayout.findViewById<RecyclerView>(R.id.common_recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager

        parentLayout.addView(innerLayout)

        return innerLayout
    }

}