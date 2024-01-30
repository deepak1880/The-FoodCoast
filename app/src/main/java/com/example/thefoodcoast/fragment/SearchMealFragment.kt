package com.example.thefoodcoast.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.thefoodcoast.base.BaseFragment
import com.example.thefoodcoast.databinding.FragmentSearchMealBinding

class SearchMealFragment : BaseFragment<FragmentSearchMealBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSearchMealBinding.inflate(inflater, container, false)
}