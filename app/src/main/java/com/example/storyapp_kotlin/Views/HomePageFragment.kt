package com.example.storyapp_kotlin.Views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.storyapp_kotlin.Adapters.ViewPagerAdapter
import com.example.storyapp_kotlin.NavigationHelper.NavigationHelper
import com.example.storyapp_kotlin.R
import com.example.storyapp_kotlin.databinding.FragmentHomePageBinding
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


class HomePageFragment  constructor() : Fragment() {

    private lateinit var binding: FragmentHomePageBinding
    private lateinit var viewPager : ViewPager2
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var fragmentList: List<Fragment>
    private lateinit var tabLayout: TabLayout
    private var clicked : Boolean = false



    //Kısaltılabilir mi diye bir bak! (Load Animation fonksiyonu tanımla!)
    private val rotateOpen : Animation by lazy { android.view.animation.AnimationUtils.loadAnimation(context, R.anim.rotate_open_anim) }
    private val rotateClose : Animation by lazy { android.view.animation.AnimationUtils.loadAnimation(context, R.anim.rotate_close_anim) }
    private val fromBottom : Animation by lazy { android.view.animation.AnimationUtils.loadAnimation(context, R.anim.from_bottom_anim) }
    private val toBottom : Animation by lazy { android.view.animation.AnimationUtils.loadAnimation(context, R.anim.to_bottom_anim) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomePageBinding.inflate(inflater, container, false)

        initializeViewPager()
        initializeTabLayout()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            fabButton.setOnClickListener { onAddButtonClicked() }
            fabStoryButton.setOnClickListener { addStoryButtonClicked() }
            fabProfileButton.setOnClickListener { profileButtonClicked() }
        }

        setupViewPagerAndTabs()
    }



    fun setupViewPagerAndTabs(){

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) { viewPager.currentItem = tab!!.position }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

    }


    private fun navigateFromHomePage(action : Int){
        val navHost =
            activity?.supportFragmentManager?.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        val navController = navHost.navController
        navController.navigate(action)


    }

    private fun addStoryButtonClicked() {
        navigateFromHomePage(R.id.action_homePageFragment_to_createStoryFragment)
    }
    private fun profileButtonClicked() {
        navigateFromHomePage(R.id.action_homePageFragment_to_profilePageFragment)

    }
    private fun onAddButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        clicked = !clicked
    }
    private fun setAnimation(clicked : Boolean) {
        with(binding){
            if (!clicked){
                fabStoryButton.startAnimation(fromBottom)
                fabProfileButton.startAnimation(fromBottom)
                fabButton.startAnimation(rotateOpen)
            }else{
                fabStoryButton.startAnimation(toBottom)
                fabProfileButton.startAnimation(toBottom)
                fabButton.startAnimation(rotateClose)
            }
        }

    }
    private fun setVisibility(clicked : Boolean) {
        with(binding){
            if (!clicked){
                fabStoryButton.visibility = View.VISIBLE
                fabProfileButton.visibility = View.VISIBLE
            }else{
                fabStoryButton.visibility = View.INVISIBLE
                fabProfileButton.visibility = View.INVISIBLE
            }
        }
    }
    fun initializeViewPager(){

        fragmentList = arrayListOf<Fragment>(CompleteTheStory(), FinishedStories())
        viewPager = binding.viewPager
        viewPagerAdapter = ViewPagerAdapter(this, fragmentList)
        viewPager.adapter = viewPagerAdapter
    }
    fun initializeTabLayout(){
        tabLayout = binding.tabLayout
        tabLayout.addTab(tabLayout.newTab().setText("Complete The Story"))
        tabLayout.addTab(tabLayout.newTab().setText("Finished Stories"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
    }
}