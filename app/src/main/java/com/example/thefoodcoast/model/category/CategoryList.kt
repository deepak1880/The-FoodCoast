package com.example.thefoodcoast.model.category

import android.os.Parcelable
import com.example.thefoodcoast.model.category.Category
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryList(
    val categories: List<Category>
) : Parcelable