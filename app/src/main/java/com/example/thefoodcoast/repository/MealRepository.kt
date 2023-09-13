package com.example.thefoodcoast.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.thefoodcoast.model.CategoryList
import com.example.thefoodcoast.model.MealList
import com.example.thefoodcoast.model.MealsByCategoryList
import com.example.thefoodcoast.retrofit.MealService

class MealRepository(private val mealService: MealService) {

    private var TAG = "MealRepository"
    private var randomMealLiveData = MutableLiveData<MealList>()
    val observerRandomMealLiveData: LiveData<MealList> get() = randomMealLiveData

    private var popularMealLiveData = MutableLiveData<MealsByCategoryList>()
    val observerPopularMealLiveData: LiveData<MealsByCategoryList> get() = popularMealLiveData

    private var categoryLiveData = MutableLiveData<CategoryList>()
    val observerCategoryLiveData: LiveData<CategoryList> get() = categoryLiveData

    var mealByCategoryLiveData = MutableLiveData<MealsByCategoryList>()
    val observerMealByCategoryList: LiveData<MealsByCategoryList> get() = mealByCategoryLiveData

    suspend fun getRandomMeal() {
        val result = mealService.getRandomMeal()
        if (result?.body() != null) {
            randomMealLiveData.value = result.body()
        }
    }

    suspend fun getPopularMeal(categoryName: String) {
        val result = mealService.getPopularMeal(categoryName)
        if (result?.body() != null) {
            popularMealLiveData.value = result.body()
        }
    }

    suspend fun getCategoryMeal() {
        val result = mealService.getCategoryMeal()
        if (result.body() != null) {
            categoryLiveData.value = result.body()
        }
    }

    suspend fun getMealByCategory(categoryName: String) {
        val result = mealService.getMealByCategory(categoryName)
        if (result.body() != null) {
            mealByCategoryLiveData.value = result.body()
        }
    }
}