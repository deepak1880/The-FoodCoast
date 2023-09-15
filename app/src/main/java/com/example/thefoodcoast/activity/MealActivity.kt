package com.example.thefoodcoast.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.thefoodcoast.databinding.ActivityMealBinding
import com.example.thefoodcoast.fragment.HomeFragment

class MealActivity : AppCompatActivity() {

    private var _binding: ActivityMealBinding? = null
    private val binding get() = _binding!!
    private var mealId: String? = null
    private var mealName: String? = null
    private var mealThumb: String? = null
    private var mealArea: String? = null
    private var mealCategory: String? = null
    private var mealInstruction: String? = null
    private var mealYoutube: String? = null
    private var TAG = "MealActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide();

        _binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getMealInformationFromIntent()
        setMealInformationInView()
        onYouTubeImageClick()

    }

    private fun onYouTubeImageClick() {
        if(mealYoutube!=null && mealYoutube!!.isNotEmpty()) {
            binding.youtube.visibility = View.VISIBLE
            binding.youtube.setOnClickListener {
                val iNext = Intent(Intent.ACTION_VIEW, Uri.parse(mealYoutube))
                startActivity(iNext)
            }
        }else{
            binding.youtube.visibility=View.GONE
        }
    }

    private fun setMealInformationInView() {
        Glide.with(applicationContext)
            .load(mealThumb)
            .into(binding.mealImageView)

        binding.mealTextView.text = mealName
        binding.mealCategory.text = mealCategory
        binding.mealArea.text = mealArea
        binding.instruction.text = mealInstruction

    }

    private fun getMealInformationFromIntent() {

        val intent = intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)
        mealArea = intent.getStringExtra(HomeFragment.MEAL_AREA)
        mealCategory = intent.getStringExtra(HomeFragment.MEAL_CATEGORY)
        mealInstruction = intent.getStringExtra(HomeFragment.MEAL_INSTRUCTION)
        mealYoutube = intent.getStringExtra(HomeFragment.MEAL_YOUTUBE)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}