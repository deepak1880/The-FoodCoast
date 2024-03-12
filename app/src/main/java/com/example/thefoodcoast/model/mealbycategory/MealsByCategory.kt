package com.example.thefoodcoast.model.mealbycategory

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MealsByCategory(
    val strMeal: String,
    val strMealThumb: String,
    val idMeal: Int
) : Parcelable