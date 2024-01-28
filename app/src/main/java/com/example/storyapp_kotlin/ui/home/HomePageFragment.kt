package com.example.storyapp_kotlin.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storyapp_kotlin.R
import com.example.storyapp_kotlin.databinding.FragmentHomePageBinding
import com.example.storyapp_kotlin.di.NavigationManager.NavigationManager
import com.example.storyapp_kotlin.ui.completed_stories.CompletedStoriesFragment
import com.example.storyapp_kotlin.ui.home.adapter.categoryRVAdapter
import com.example.storyapp_kotlin.ui.in_progress.InProgressStories
import com.example.storyapp_kotlin.ui.trending.TrendingFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomePageFragment : Fragment() {

    private lateinit var binding: FragmentHomePageBinding
    private val homePageViewModel: HomePageViewModel by viewModels()
    private lateinit var categoryRVadapter : categoryRVAdapter

    private val trendingFragment by lazy { TrendingFragment() }
    private val completedStoriesFragment by lazy { CompletedStoriesFragment() }
    private val inProgressStoriesFragment by lazy { InProgressStories() }

    @Inject
    lateinit var navigationManager: NavigationManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomePageBinding.inflate(inflater, container, false)
        initalizeRV()
        setFragment(trendingFragment)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //Stringlerle çalışmak yerine daha güvenilir bir çözüm yolu bul!
        categoryRVadapter.onCategoryClick = {
            println("Category Clicked")
            when(it){
                "Trending" -> setFragment(trendingFragment)
                "Completed Stories" -> setFragment(completedStoriesFragment)
                "In Progress Stories" -> setFragment(inProgressStoriesFragment)
            }
        }

    }



    private fun setFragment(fragment: Fragment){

        //Burada existing fragment olayını incele. Eğer bottom nav ile uyumlu yapabiliyorsan ilerisi için daha iyi olur!
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.homeFrameLayout, fragment, fragment.javaClass.simpleName)
            .commit()
    }

    private fun initalizeRV(){
        categoryRVadapter = categoryRVAdapter()
        binding.categoryReyclerView.adapter = categoryRVadapter
        binding.categoryReyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
    }

}