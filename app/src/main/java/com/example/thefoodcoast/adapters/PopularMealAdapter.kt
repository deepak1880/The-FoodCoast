package com.example.thefoodcoast.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.thefoodcoast.databinding.PopularMealBinding
import com.example.thefoodcoast.model.MealsByCategory

class
PopularMealAdapter() : RecyclerView.Adapter<PopularMealAdapter.PopularMealViewHolder>() {

    private var mealsList = ArrayList<MealsByCategory>()

    fun setMeals(mealsList: List<MealsByCategory>) {
        this.mealsList.clear()
        this.mealsList.addAll(mealsList)
        notifyDataSetChanged()
    }

    var itemOnClick: ((MealsByCategory) -> Unit)? = null

    class PopularMealViewHolder(var binding: PopularMealBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMealViewHolder {
        val binding = PopularMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularMealViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }

    override fun onBindViewHolder(holder: PopularMealViewHolder, position: Int) {

        Glide.with(holder.itemView)
            .load(mealsList[position].strMealThumb)
            .into(holder.binding.popularImage)

        holder.itemView.setOnClickListener {
            itemOnClick?.invoke(mealsList[position])
        }
    }
}