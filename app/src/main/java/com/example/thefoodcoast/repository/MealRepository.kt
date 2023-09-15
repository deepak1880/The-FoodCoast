package com.example.thefoodcoast.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.thefoodcoast.model.CategoryList
import com.example.thefoodcoast.model.MealList
import com.example.thefoodcoast.model.MealsByCategoryList
import com.example.thefoodcoast.retrofit.MealService
import com.example.thefoodcoast.retrofit.Response

class MealRepository(private val mealService: MealService) {

    private var TAG = "MealRepository"
    private var randomMealLiveData = MutableLiveData<Response<MealList>>()
    val observerRandomMealLiveData: LiveData<Response<MealList>> get() = randomMealLiveData

    private var popularMealLiveData = MutableLiveData<Response<MealsByCategoryList>>()
    val observerPopularMealLiveData: LiveData<Response<MealsByCategoryList>>get() = popularMealLiveData

    private var categoryLiveData = MutableLiveData<Response<CategoryList>>()
    val observerCategoryLiveData: LiveData<Response<CategoryList>>get() = categoryLiveData

    var mealByCategoryLiveData = MutableLiveData<Response<MealsByCategoryList>>()
    val observerMealByCategoryList: LiveData<Response<MealsByCategoryList>> get() = mealByCategoryLiveData

    private var searchMealLiveData = MutableLiveData<MealList>()
    val observerSearchMeal: LiveData<MealList> get() = searchMealLiveData

    suspend fun getRandomMeal() {

        val result = mealService.getRandomMeal()
        if (result?.body() != null) {

            randomMealLiveData.value = Response.Success(result.body())
        } else {
            randomMealLiveData.value =
                Response.Failure("Error found while fetching the Random Meal")
        }
    }

    suspend fun getPopularMeal(categoryName: String) {
        val result = mealService.getPopularMeal(categoryName)
        if (result?.body() != null) {
            popularMealLiveData.value = Response.Success(result.body())
        }else{
            popularMealLiveData.value=
            Response.Failure("Error found while fetching the Getting Meal")
        }
    }

    suspend fun getCategoryMeal() {
        val result = mealService.getCategoryMeal()
        if (result.body() != null) {
            categoryLiveData.value = Response.Success(result.body())
        }else{
            categoryLiveData.value=
                Response.Failure("Error found while fetching the Getting Meal")
        }
    }

    suspend fun getMealByCategory(categoryName: String) {
        val result = mealService.getMealByCategory(categoryName)
        if (result.body() != null) {
            mealByCategoryLiveData.value = Response.Success(result.body())
        }else{
            mealByCategoryLiveData.value=
                Response.Failure("Error found while fetching the Getting Meal")
        }
    }

    suspend fun seachMeal(mealName: String) {
        val result = mealService.searchMeal(mealName)
        if (result.body() != null) {
            searchMealLiveData.value = result.body()
        }
    }
}