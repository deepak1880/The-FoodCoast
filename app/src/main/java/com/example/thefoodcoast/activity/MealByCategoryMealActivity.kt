package com.example.thefoodcoast.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.thefoodcoast.R
import com.example.thefoodcoast.adapters.MealByCategoryAdapter
import com.example.thefoodcoast.databinding.ActivityCategoryMealBinding
import com.example.thefoodcoast.fragment.HomeFragment
import com.example.thefoodcoast.model.category.Category
import com.example.thefoodcoast.repository.MealRepository
import com.example.thefoodcoast.retrofit.RetrofitInstance.retrofit
import com.example.thefoodcoast.viewModel.MealByCategoryViewModel
import com.example.thefoodcoast.viewModel.MealByCategoryViewModelFactory
import kotlinx.coroutines.launch

class MealByCategoryMealActivity : AppCompatActivity() {

    private var TAG = "CategoryMeal"
    private var _binding: ActivityCategoryMealBinding? = null
    private val binding get() = _binding!!

    private var categoryViewModel: MealByCategoryViewModel? = null
    private var mealByCategoryAdapter: MealByCategoryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        _binding = ActivityCategoryMealBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()
        mealByCategoryRecyclerView()

        val categoryMeal = intent.getParcelableExtra<Category>(HomeFragment.CATEGORY_MEAL)
        val mealService = retrofit
        val repository = MealRepository(mealService)

        categoryMeal?.let {
            categoryViewModel = ViewModelProvider(
                this,
                MealByCategoryViewModelFactory(repository, it.strCategory)
            )[MealByCategoryViewModel::class.java]

        }
        lifecycleScope.launch {
            categoryViewModel?.mealsByCategory?.collect {
                binding.categoryMealTextView.text =
                    resources.getString(R.string.total_meal, it.data?.meals?.size.toString())
                it.data?.let { it1 -> mealByCategoryAdapter?.setMeal(it1.meals) }
            }
        }
    }

    private fun mealByCategoryRecyclerView() {
        mealByCategoryAdapter = MealByCategoryAdapter()
        binding.categoryRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = mealByCategoryAdapter
        }
    }
}