package com.example.thefoodcoast.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.thefoodcoast.databinding.CategoryMealBinding
import com.example.thefoodcoast.model.Category

class CategoriesAdapter() : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    private var categoriesList = ArrayList<Category>()

    fun setCategoryList(categoriesList: List<Category>) {
        this.categoriesList = categoriesList as ArrayList<Category>
        notifyDataSetChanged()
    }

    class CategoryViewHolder(var binding: CategoryMealBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            CategoryMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(categoriesList[position].strCategoryThumb)
            .into(holder.binding.categoryImageView)

        holder.binding.categoryTextView.text=categoriesList[position].strCategory
    }

}