package com.example.thefoodcoast.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.thefoodcoast.databinding.CategoryItemBinding
import com.example.thefoodcoast.model.MealsByCategory

class MealByCategoryAdapter : RecyclerView.Adapter<MealByCategoryAdapter.CategoryViewHolder>() {

    private var mealList = ArrayList<MealsByCategory>()

    fun setMeal(mealList: List<MealsByCategory>) {
        this.mealList = mealList as ArrayList<MealsByCategory>
        notifyDataSetChanged()
    }

    class CategoryViewHolder(var binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        Glide.with(holder.itemView).load(mealList[position].strMealThumb)
            .into(holder.binding.itemImageView)
        holder.binding.itemTextView.text = mealList[position].strMeal

    }
}