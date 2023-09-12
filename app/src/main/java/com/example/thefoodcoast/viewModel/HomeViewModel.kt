package com.example.thefoodcoast.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thefoodcoast.model.Category
import com.example.thefoodcoast.model.CategoryList
import com.example.thefoodcoast.model.Meal
import com.example.thefoodcoast.model.MealList
import com.example.thefoodcoast.model.MealsByCategory
import com.example.thefoodcoast.model.MealsByCategoryList
import com.example.thefoodcoast.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private var TAG = "HomeViewModel"

    private var randomMealLiveData = MutableLiveData<Meal>()
    val observerRandomMealData: LiveData<Meal> get() = randomMealLiveData

    private var popularMealLiveData = MutableLiveData<List<MealsByCategory>>()
    val observerPopularMealData: LiveData<List<MealsByCategory>> get() = popularMealLiveData

    private var categoryLiveData = MutableLiveData<List<Category>>()
    val observerCategoryLiveData: LiveData<List<Category>> get() = categoryLiveData


    init {
        getRandomMeal()
        getPopularMeal()
        getCategoryMeal()
    }

    private fun getCategoryMeal() {
        RetrofitInstance.retrofit.getCategoryMeal().enqueue(object : Callback<CategoryList> {
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                if (response.body() != null) {
                    val category = response.body()
                    if (category != null) {
                        categoryLiveData.value = category.categories
                    }
                }
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })

    }

    private fun getPopularMeal() {
        RetrofitInstance.retrofit.getPopularMeal("Seafood")
            .enqueue(object : Callback<MealsByCategoryList> {
                override fun onResponse(
                    call: Call<MealsByCategoryList>,
                    response: Response<MealsByCategoryList>
                ) {
                    if (response.body() != null) {
                        val popularMeal = response.body()
                        Log.e(TAG, "onResponse: ${popularMeal?.meals}")
                        popularMealLiveData.value = popularMeal?.meals!!
                    } else {
                        return
                    }
                }

                override fun onFailure(call: Call<MealsByCategoryList>, t: Throwable) {
                    Log.e("HomeViewModel", t.message.toString())
                }
            })
    }


    private fun getRandomMeal() {

        RetrofitInstance.retrofit.getRandomMeal().enqueue(object : Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {

                if (response.body() != null) {
                    val randomMeal: Meal = response.body()!!.meals[0]
                    randomMealLiveData.value = randomMeal
                } else {
                    return
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.e("HomeViewModel", t.message.toString())
                t.printStackTrace()
            }
        })

    }
}
