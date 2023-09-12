package com.example.thefoodcoast.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MealList(
    val meals: List<Meal>
) : Parcelable