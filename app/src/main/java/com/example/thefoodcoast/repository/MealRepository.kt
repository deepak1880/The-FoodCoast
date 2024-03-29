package com.example.thefoodcoast.repository

import com.example.thefoodcoast.model.category.CategoryList
import com.example.thefoodcoast.model.meal.MealList
import com.example.thefoodcoast.model.mealbycategory.MealsByCategoryList
import com.example.thefoodcoast.retrofit.MealService
import com.example.thefoodcoast.retrofit.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MealRepository(private val mealService: MealService) {

    private var TAG = "MealRepository"

    fun getRandomMeal(): Flow<Response<MealList>> = flow {
        try {
            emit(Response.Loading())
            val result = mealService.getRandomMeal()
            if (result?.body() != null) {
                emit(Response.Success(result.body()))
            } else {
                emit(Response.Failure("Error found while fetching the Random Meal"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }.flowOn(Dispatchers.IO)

    fun getPopularMeal(categoryName: String): Flow<Response<MealsByCategoryList>> = flow {
        try {
            emit(Response.Loading())
            val result = mealService.getPopularMeal(categoryName)
            if (result?.body() != null) {
                emit(Response.Success(result.body()))
            } else {
                emit(Response.Failure("Error found while fetching the Getting Meal"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }.flowOn(Dispatchers.IO)

    fun getCategoryMeal(): Flow<Response<CategoryList>> = flow {
        try {
            emit(Response.Loading())
            val result = mealService.getCategoryMeal()
            if (result.body() != null) {
                emit(Response.Success(result.body()))
            } else {
                emit(
                    Response.Failure("Error found while fetching the Getting Meal")
                )
            }
        } catch (e: Exception) {
            emit(
                Response.Failure("Error found while fetching the Getting Meal")
            )
        }
    }.flowOn(Dispatchers.IO)

    fun getMealByCategory(categoryName: String): Flow<Response<MealsByCategoryList>> = flow {
        try {
            emit(Response.Loading())
            val result = mealService.getMealByCategory(categoryName)
            if (result.body() != null) {
                emit(Response.Success(result.body()))
            } else {
                emit(
                    Response.Failure("Error found while fetching the Getting Meal")
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }.flowOn(Dispatchers.IO)

    fun searchMeal(): Flow<Response<MealList>> = flow {
        try {
            emit(Response.Loading())
            val result = mealService.searchMeal()
            if (result.body() != null) {
                emit(Response.Success(result.body()))
            } else {
                emit(
                    Response.Failure("Error found while fetching the Getting Meal")
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }.flowOn(Dispatchers.IO)
}