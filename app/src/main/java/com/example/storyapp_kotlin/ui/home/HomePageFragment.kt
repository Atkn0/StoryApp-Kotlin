package com.example.storyapp_kotlin.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager2.widget.ViewPager2
import com.example.storyapp_kotlin.ui.home.adapter.ViewPagerAdapter
import com.example.storyapp_kotlin.R
import com.example.storyapp_kotlin.ui.finishedStories.FinishedStories
import com.example.storyapp_kotlin.databinding.FragmentHomePageBinding
import com.example.storyapp_kotlin.di.NavigationManager.NavigationManager
import com.example.storyapp_kotlin.ui.completeTheStory.CompleteTheStory
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomePageFragment : Fragment() {

    private lateinit var binding: FragmentHomePageBinding
    private lateinit var viewPager : ViewPager2
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var fragmentList: List<Fragment>
    private lateinit var tabLayout: TabLayout
    private var clicked : Boolean = false

    @Inject
    lateinit var navigationManager: NavigationManager

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
    ): View {
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
            fabProfileButton.setOnClickListener {
                addProfileButtonClicked()
            }
        }

        setupViewPagerAndTabs()
    }

    private fun addProfileButtonClicked() {
        //navigateFromHomePage(R.id.action_homePageFragment_to_profilePageFragment)
        val directions = HomePageFragmentDirections.actionHomePageFragmentToProfilePageFragment()
        navigationManager.navigateTo(directions)
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

    /*
    private fun navigateFromHomePage(action : Int){
        //Her sayfaya yazmak yerine daha mantıklı bir çözüm bulunabilir! (Navigation Helper oluştur!)
        val navHost =
            activity?.supportFragmentManager?.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        val navController = navHost.navController
        navController.navigate(action)
    }

     */
    private fun addStoryButtonClicked() {
        val directions = HomePageFragmentDirections.actionHomePageFragmentToCreateStoryFragment()
        navigationManager.navigateTo(directions)
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