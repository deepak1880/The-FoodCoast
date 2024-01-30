package com.example.thefoodcoast.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thefoodcoast.model.CategoryList
import com.example.thefoodcoast.model.MealList
import com.example.thefoodcoast.model.MealsByCategory
import com.example.thefoodcoast.model.MealsByCategoryList
import com.example.thefoodcoast.repository.MealRepository
import com.example.thefoodcoast.retrofit.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HomeViewModel( repository: MealRepository) : ViewModel() {

    private var TAG = "HomeViewModel"

//    init {
//        viewModelScope.launch(Dispatchers.IO) {
//
//            respository.getRandomMeal()
//            respository.getPopularMeal("Seafood")
//            respository.getCategoryMeal()
//            respository.seachMeal("Seafood")
//        }
//    }

    val randomMeal :Flow<Response<MealList>> = repository.getRandomMeal()
    val popularMeal:Flow<Response<MealsByCategoryList>> =repository.getPopularMeal("Seafood")
    val categoryMeal:Flow<Response<CategoryList>> = repository.getCategoryMeal()
    val searchMeal:Flow<Response<MealList>> =repository.searchMeal()

    // val observerRandomMealLiveData: Response<MealList>get() = respository.getRandomMeal().asLiveData(viewModelScope).value!!

//    val observerPopularMealLiveData: LiveData<Response<MealsByCategoryList>> get() = respository.observerPopularMealLiveData
//    val observerCategoryLiveData: LiveData<Response<CategoryList>> get() = respository.observerCategoryLiveData
//    val observerSearchMeal: LiveData<MealList> get() = respository.observerSearchMeal

    }


