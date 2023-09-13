package com.example.thefoodcoast.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.thefoodcoast.repository.MealRepository

class MealByCategoryViewModelFactory(val repository: MealRepository, private val categoryName: String) :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MealByCategoryViewModel(repository, categoryName) as T
    }


}