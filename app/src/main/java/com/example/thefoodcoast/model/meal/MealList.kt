package com.example.thefoodcoast.model.meal

import android.os.Parcelable
import com.example.thefoodcoast.model.meal.Meal
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MealList(
    val meals: List<Meal>
) : Parcelable