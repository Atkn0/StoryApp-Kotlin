package com.example.storyapp_kotlin.ui.library

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storyapp_kotlin.databinding.FragmentLibraryBinding
import com.example.storyapp_kotlin.domain.usecase.GetAllUsersUseCase
import com.example.storyapp_kotlin.domain.usecase.GetStoriesByCollectionUseCase
import com.example.storyapp_kotlin.models.StoryModel
import com.example.storyapp_kotlin.models.UserModel
import com.example.storyapp_kotlin.utils.common_rv.commonRVadapter
import com.example.storyapp_kotlin.ui.home.adapter.categoryRVAdapter
import com.example.storyapp_kotlin.utils.RecyclerViewBuilder.RecyclerViewBuilder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LibraryFragment @Inject constructor() : Fragment() {

    private lateinit var binding: FragmentLibraryBinding
    lateinit var categoryRVadapter : categoryRVAdapter
    private lateinit var commonRVadapter: commonRVadapter

    private var fragment_width : Int = 0
    private var fragment_height : Int = 0

    private var dpWidth = 0
    private var dpHeight = 0





    private val libraryViewModel : LibraryFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLibraryBinding.inflate(inflater, container, false)
        val displayMetrics = resources.displayMetrics
        dpWidth = displayMetrics.widthPixels / 2
        dpHeight = (displayMetrics.heightPixels * 4) / 10
        initalizeCategory()
        libraryViewModel.loadDataAndCallFunction(::initializeStoriesRV)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.postDelayed({
            // FrameLayout'ın genişliğini al
            fragment_width = binding.fragmentLibraryConstraintLayout.width

            println("dpwidth = " + dpWidth)

            println("frameLayoutWidth: $fragment_width")
        }, 100)


        //categoryAdapter.onCategoryClick = ::updateStoriesForCategory ile kısaltılabilir
        categoryRVadapter.onCategoryClick = {category ->
            when(category){
                "Saved Books" -> {
                    libraryViewModel.loadDataAndCallFunction(::updateAdapter)
                }
                "Participated Stories" -> {
                    libraryViewModel.loadDataAndCallFunction(::updateAdapter)
                }
                "My Creations" -> {
                    libraryViewModel.loadDataAndCallFunction(::updateAdapter)
                }
            }
        }

    }

/*
    1 - Library Fragment içerisinde her kategoriyi kapsayacak şekilde ortak bir RV oluşturulacak
    2 - Her kategoriye tıklandığında o kategoriye ait verileri getirip RV içerisine yerleştirecek
    3 - adapter içersinde listeyi güncelleyecek bir update fonksiyonu oluşturulacak
    4 - Her kategoriye ait verileri getStoriesByCollection() fonksiyonu ile getiricez
 */

    private fun initalizeCategory(){
        val categoryList = arrayListOf("Saved Books","Participated Stories","My Creations")
        categoryRVadapter = categoryRVAdapter(categoryList)
        binding.libraryCategoryRecyclerView.adapter = categoryRVadapter
        binding.libraryCategoryRecyclerView.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL,false)
    }

    private fun initializeStoriesRV(storiesList : ArrayList<StoryModel>,userList : ArrayList<UserModel>){
        commonRVadapter = commonRVadapter(storiesList,userList, width = dpWidth, height = dpHeight)
        binding.libraryStoriesRecyclerView.adapter = commonRVadapter
        binding.libraryStoriesRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)
    }


    private fun updateAdapter(newStoryList : ArrayList<StoryModel>,newUserList : ArrayList<UserModel>){
        commonRVadapter.updateData(newStoryList,newUserList)
        commonRVadapter.notifyDataSetChanged()
    }





}