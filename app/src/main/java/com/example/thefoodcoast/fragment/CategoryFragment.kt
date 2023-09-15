package com.example.thefoodcoast.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.thefoodcoast.activity.MealByCategoryMealActivity
import com.example.thefoodcoast.adapters.CategoriesAdapter
import com.example.thefoodcoast.databinding.FragmentCategoriesBinding
import com.example.thefoodcoast.fragment.HomeFragment.Companion.CATEGORY_MEAL
import com.example.thefoodcoast.repository.MealRepository
import com.example.thefoodcoast.retrofit.RetrofitInstance.retrofit
import com.example.thefoodcoast.viewModel.HomeViewModel
import com.example.thefoodcoast.viewModel.HomeViewModelFactory

class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding
    private var categoryViewModel: HomeViewModel? = null
    private var categoryAdapter: CategoriesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mealService=retrofit
        val repository=MealRepository(mealService)
        categoryViewModel=ViewModelProvider(this,HomeViewModelFactory(repository))[HomeViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observecategoryMeal()
        categoryRecyclerView()
        onClickCategoryMeal()

    }
    private fun onClickCategoryMeal() {
        val bundle = Bundle()
        categoryAdapter?.itemOnClick = { category ->
            val iNext = Intent(activity, MealByCategoryMealActivity::class.java)
            bundle.putParcelable(CATEGORY_MEAL, category)
            iNext.putExtras(bundle)
            startActivity(iNext)

        }
    }

    private fun categoryRecyclerView() {
        categoryAdapter=CategoriesAdapter()
        binding?.categoryRecyclerView?.apply {
            layoutManager=GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false)
            adapter=categoryAdapter
        }
    }

    private fun observecategoryMeal() {
        categoryViewModel?.observerCategoryLiveData?.observe(
            viewLifecycleOwner
        ) { categories ->
            categories.data?.let { categoryAdapter?.setCategoryList(it.categories) }
        }
    }
    }

