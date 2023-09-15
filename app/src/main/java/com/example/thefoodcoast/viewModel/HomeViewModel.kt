package com.example.thefoodcoast.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thefoodcoast.model.CategoryList
import com.example.thefoodcoast.model.MealList
import com.example.thefoodcoast.model.MealsByCategoryList
import com.example.thefoodcoast.repository.MealRepository
import com.example.thefoodcoast.retrofit.Response
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
            respository.seachMeal("Seafood")
        }
    }

    val observerRandomMealLiveData: LiveData<Response<MealList>> get() = respository.observerRandomMealLiveData
    val observerPopularMealLiveData: LiveData<Response<MealsByCategoryList>> get() = respository.observerPopularMealLiveData
    val observerCategoryLiveData: LiveData<Response<CategoryList>> get() = respository.observerCategoryLiveData
    val observerSearchMeal: LiveData<MealList> get() = respository.observerSearchMeal


}
