package com.example.thefoodcoast.model.mealbycategory

import android.os.Parcelable
import com.example.thefoodcoast.model.mealbycategory.MealsByCategory
import kotlinx.parcelize.Parcelize

@Parcelize
data class MealsByCategoryList(
    val meals: List<MealsByCategory>
) : Parcelable