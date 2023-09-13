package com.example.thefoodcoast.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thefoodcoast.model.CategoryList
import com.example.thefoodcoast.model.MealList
import com.example.thefoodcoast.model.MealsByCategoryList
import com.example.thefoodcoast.repository.MealRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val respository: MealRepository) : ViewModel() {

    private var TAG = "HomeViewModel"

    init {
        viewModelScope.launch {
            (Dispatchers.IO)
            respository.getRandomMeal()
            respository.getPopularMeal("Seafood")
            respository.getCategoryMeal()
        }
    }

    val observerRandomMealLiveData: LiveData<MealList> get() = respository.observerRandomMealLiveData
    val observerPopularMealLiveData: LiveData<MealsByCategoryList> get() = respository.observerPopularMealLiveData
    val observerCategoryLiveData: LiveData<CategoryList> get() = respository.observerCategoryLiveData

}
