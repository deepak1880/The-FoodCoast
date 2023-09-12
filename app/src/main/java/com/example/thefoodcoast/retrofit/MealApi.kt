package com.example.thefoodcoast.retrofit

import com.example.thefoodcoast.model.CategoryList
import com.example.thefoodcoast.model.MealList
import com.example.thefoodcoast.model.MealsByCategoryList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("random.php")
    fun getRandomMeal(): Call<MealList>

    @GET("filter.php?")
    fun getPopularMeal(@Query("c") categoryName: String): Call<MealsByCategoryList>

    @GET("categories.php")
    fun getCategoryMeal(): Call<CategoryList>
}