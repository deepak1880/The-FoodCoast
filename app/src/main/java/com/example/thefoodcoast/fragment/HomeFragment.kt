package com.example.thefoodcoast.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.thefoodcoast.activity.MealActivity
import com.example.thefoodcoast.adapters.CategoriesAdapter
import com.example.thefoodcoast.adapters.PopularMealAdapter
import com.example.thefoodcoast.databinding.FragmentHomeBinding
import com.example.thefoodcoast.model.Meal
import com.example.thefoodcoast.viewModel.HomeViewModel

class HomeFragment : Fragment() {

    private val TAG = "HomeFragment"
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()
    private var randomMeal: Meal? = null
    private var popularAdapter: PopularMealAdapter? = null
    private var categoriesAdapter:CategoriesAdapter?=null

    companion object {
        const val MEAL_ID = "idMeal"
        const val MEAL_THUMB = "mealThumb"
        const val MEAL_NAME = "mealName"
        const val MEAL_AREA="mealArea"
        const val MEAL_CATEGORY="mealCategory"
        const val MEAL_INSTRUCTION="mealInstruction"
        const val MEAL_YOUTUBE="mealYoutube"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        popularAdapter = PopularMealAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        //Log.e(TAG, "onCreateView: ")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observerRandomMealDataLiveData()
        onRandomMealClick()

        observerPopularMealLiveData()
        popularMealRecyclerView()
        onClickPopularMeal()

        observerCategoryLiveData()
        categoryRecyclerView()

    }

    private fun categoryRecyclerView() {
        categoriesAdapter=CategoriesAdapter()
       binding.categoryRecyclerView.apply {
           layoutManager=GridLayoutManager(context,3,GridLayoutManager.HORIZONTAL,false)
           adapter=categoriesAdapter
       }
    }

    private fun observerCategoryLiveData() {
        homeViewModel.observerCategoryLiveData.observe(viewLifecycleOwner
        ){categories->
           categoriesAdapter?.setCategoryList(categories)
        }
    }

    private fun onClickPopularMeal() {
        popularAdapter?.itemOnClick = {
           val bundle=Bundle().apply {
               putInt(MEAL_ID,it.idMeal)
               putString(MEAL_THUMB,it.strMealThumb)
               putString(MEAL_NAME,it.strMeal)
           }
            val iNext = Intent(activity, MealActivity::class.java)
            iNext.putExtras(bundle)
            startActivity(iNext)
        }
    }

    private fun popularMealRecyclerView() {
        binding.overPopularRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularAdapter
        }
    }

    private fun observerPopularMealLiveData() {
        homeViewModel.observerPopularMealData.observe(
            viewLifecycleOwner
        ) {
            Log.e(TAG, "observerPopularMeal: $it")
            popularAdapter?.setMeals(mealsList = it)
        }
    }

    private fun onRandomMealClick() {
        binding.cardViewMealImage.setOnClickListener {
            val bundle = Bundle().apply {
                randomMeal?.let {
                    putInt(MEAL_ID, it.idMeal)
                    putString(MEAL_NAME, it.strMeal)
                    putString(MEAL_THUMB, it.strMealThumb)
                    putString(MEAL_AREA, it.strArea)
                    putString(MEAL_CATEGORY, it.strCategory)
                    putString(MEAL_INSTRUCTION, it.strInstructions)
                    putString(MEAL_YOUTUBE, it.strYoutube)
                }
            }
            val iNext = Intent(activity, MealActivity::class.java)
            iNext.putExtras(bundle)
            startActivity(iNext)
        }
    }

    private fun observerRandomMealDataLiveData() {
        homeViewModel?.observerRandomMealData?.observe(
            viewLifecycleOwner
        ) { value ->

            Glide.with(this@HomeFragment)
                .load(value!!.strMealThumb)
                .into(binding.cardViewMealImage)
            this.randomMeal = value
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}