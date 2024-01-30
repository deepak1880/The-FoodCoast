package com.example.thefoodcoast.fragment

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.thefoodcoast.activity.MealActivity
import com.example.thefoodcoast.activity.MealByCategoryMealActivity
import com.example.thefoodcoast.adapters.CategoriesAdapter
import com.example.thefoodcoast.adapters.PopularMealAdapter
import com.example.thefoodcoast.databinding.FragmentHomeBinding
import com.example.thefoodcoast.model.Meal
import com.example.thefoodcoast.receiver.ConnectivityReceiver
import com.example.thefoodcoast.repository.MealRepository
import com.example.thefoodcoast.retrofit.Response
import com.example.thefoodcoast.retrofit.RetrofitInstance.retrofit
import com.example.thefoodcoast.viewModel.HomeViewModel
import com.example.thefoodcoast.viewModel.HomeViewModelFactory
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private val TAG = "HomeFragment"
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var homeViewModel: HomeViewModel? = null
    private var randomMeal: List<Meal>? = null
    private var popularAdapter: PopularMealAdapter? = null
    private var categoriesAdapter: CategoriesAdapter? = null

    private val connectivityReceiver = ConnectivityReceiver {
        Log.e(TAG, ": ")
        if (it) {
            Toast.makeText(requireContext(), "Internet Connected", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "No Internet", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val MEAL_ID = "idMeal"
        const val MEAL_THUMB = "mealThumb"
        const val MEAL_NAME = "mealName"
        const val MEAL_AREA = "mealArea"
        const val MEAL_CATEGORY = "mealCategory"
        const val MEAL_INSTRUCTION = "mealInstruction"
        const val MEAL_YOUTUBE = "mealYoutube"
        const val CATEGORY_MEAL = "categoryMeal"
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

        popularAdapter = PopularMealAdapter()

        val mealService = retrofit
        val repository = MealRepository(mealService)
        homeViewModel =
            ViewModelProvider(this, HomeViewModelFactory(repository))[HomeViewModel::class.java]



        observerRandomMealDataLiveData()
        onRandomMealClick()

        observerPopularMealLiveData()
        popularMealRecyclerView()
        onClickPopularMeal()

        observerCategoryLiveData()
        categoryRecyclerView()
        onClickCategoryMeal()

    }

    override fun onResume() {
        super.onResume()
        val intent = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        requireActivity().registerReceiver(connectivityReceiver, intent)
    }

    private fun onClickCategoryMeal() {
        val bundle = Bundle()
        categoriesAdapter?.itemOnClick = { category ->
            val iNext = Intent(activity, MealByCategoryMealActivity::class.java)
            bundle.putParcelable(CATEGORY_MEAL, category)
            iNext.putExtras(bundle)
            startActivity(iNext)

        }
    }

    private fun categoryRecyclerView() {
        categoriesAdapter = CategoriesAdapter()
        binding.categoryRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
            adapter = categoriesAdapter
        }
    }

    private fun observerCategoryLiveData() {
        lifecycleScope.launch {
            homeViewModel?.categoryMeal?.collect(
            ) { categories ->
                when (categories) {
                    is Response.Loading -> {
                        binding.shimmer.visibility = View.VISIBLE
                        binding.categoryCardView.visibility = View.VISIBLE
                    }

                    is Response.Success -> {
                        categories.data?.let { categoriesAdapter?.setCategoryList(it.categories) }
                    }

                    is Response.Failure -> {
                        categories.errorMessage
                        Toast.makeText(
                            context,
                            categories.errorMessage.toString(),
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                }
            }
        }
    }

    private fun onClickPopularMeal() {
        popularAdapter?.itemOnClick = {
            val bundle = Bundle().apply {
                putInt(MEAL_ID, it.idMeal)
                putString(MEAL_THUMB, it.strMealThumb)
                putString(MEAL_NAME, it.strMeal)
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
        lifecycleScope.launch {
            homeViewModel?.popularMeal?.collect(
            ) {
                when (it) {
                    is Response.Loading -> {
                        binding.shimmer.visibility = View.VISIBLE
                        binding.overPopularCardView.visibility = View.VISIBLE
                    }

                    is Response.Success -> {
                        it.data?.let { it1 -> popularAdapter?.setMeals(mealsList = it1.meals) }
                        binding.shimmer.visibility = View.GONE
                    }

                    is Response.Failure -> {
                        it.errorMessage
                        Toast.makeText(context, it.errorMessage.toString(), Toast.LENGTH_LONG)
                            .show()
                    }
                }

            }
        }
    }

    private fun onRandomMealClick() {
        binding.cardViewMealImage.setOnClickListener {
            val bundle = Bundle().apply {
                randomMeal?.get(0)?.let {
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
        lifecycleScope.launch {
            homeViewModel?.randomMeal?.collect { mealList ->
                Log.e(TAG, "observerRandomMealDataLiveData: Success")
                when (mealList) {
                    is Response.Loading -> {
                        Log.e(TAG, "observerRandomMealDataLiveData: ")
                        binding.shimmer.visibility = View.VISIBLE
                        binding.mealCardView.visibility = View.VISIBLE
                    }

                    is Response.Success -> {
                        randomMeal = mealList.data?.meals
                        Glide.with(this@HomeFragment)
                            .load(mealList.data!!.meals[0]?.strMealThumb)
                            .into(binding.cardViewMealImage)
                        binding.shimmer.visibility = View.GONE
                    }

                    is Response.Failure -> {
                        mealList.errorMessage
                        Toast.makeText(
                            context,
                            mealList.errorMessage.toString(),
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                }
            }
        }
    }


    override fun onStop() {
        super.onStop()
        requireActivity().unregisterReceiver(connectivityReceiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}