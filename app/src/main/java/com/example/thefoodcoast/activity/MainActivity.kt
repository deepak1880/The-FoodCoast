package com.example.thefoodcoast.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.thefoodcoast.R
import com.example.thefoodcoast.databinding.ActivityMainBinding
import com.example.thefoodcoast.fragment.CategoryFragment
import com.example.thefoodcoast.fragment.FavouriteFragment
import com.example.thefoodcoast.fragment.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        binding.bottomNavigation.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.homeFragment -> replaceFragment(HomeFragment())
                R.id.favouritesFragment -> replaceFragment(FavouriteFragment())
                R.id.categoriesFragment -> replaceFragment(CategoryFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.frameLayout, fragment)
        ft.commit()
    }
}