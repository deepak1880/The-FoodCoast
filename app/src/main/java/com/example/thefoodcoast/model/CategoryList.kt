package com.example.thefoodcoast.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryList(
    val categories:List<Category>
) : Parcelable