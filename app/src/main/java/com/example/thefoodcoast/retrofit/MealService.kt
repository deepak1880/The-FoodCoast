package com.example.thefoodcoast.retrofit

import com.example.thefoodcoast.model.CategoryList
import com.example.thefoodcoast.model.MealList
import com.example.thefoodcoast.model.MealsByCategoryList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealService {

    @GET("random.php")
    suspend fun getRandomMeal(): Response<MealList>

    @GET("filter.php?")
    suspend fun getPopularMeal(@Query("c") categoryName: String): Response<MealsByCategoryList>

    @GET("categories.php")
    suspend fun getCategoryMeal(): Response<CategoryList>

    @GET("filter.php")
    suspend fun getMealByCategory(@Query("c") categoryName: String): Response<MealsByCategoryList>
}