package com.example.thefoodcoast.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thefoodcoast.model.MealsByCategoryList
import com.example.thefoodcoast.repository.MealRepository
import kotlinx.coroutines.launch

class MealByCategoryViewModel(private val respository: MealRepository, categoryName: String) :
    ViewModel() {

    init {
        viewModelScope.launch {
            respository.getMealByCategory(categoryName)
        }
    }

    val observerMealByCategoryList: LiveData<MealsByCategoryList> get() = respository.observerMealByCategoryList


}