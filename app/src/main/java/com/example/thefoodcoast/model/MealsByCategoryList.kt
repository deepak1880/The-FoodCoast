package com.example.thefoodcoast.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MealsByCategoryList(
    val meals: List<MealsByCategory>
) : Parcelable