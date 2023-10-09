package com.example.thefoodcoast.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thefoodcoast.model.MealsByCategoryList
import com.example.thefoodcoast.repository.MealRepository
import com.example.thefoodcoast.retrofit.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MealByCategoryViewModel(private val repository: MealRepository, categoryName: String) :
    ViewModel() {

//    init {
//        viewModelScope.launch {
//            repository.getMealByCategory(categoryName)
//        }
//    }
    val mealsByCategory: Flow<Response<MealsByCategoryList>> = repository.getMealByCategory(categoryName)


   // val observerMealByCategoryList: LiveData<Response<MealsByCategoryList>>get() = respository.observerMealByCategoryList


}