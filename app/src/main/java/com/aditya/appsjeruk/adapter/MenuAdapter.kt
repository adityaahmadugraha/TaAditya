package com.aditya.appsjeruk.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.aditya.appsjeruk.user.ui.home.HomeFragment
import com.aditya.appsjeruk.user.ui.home.fragment.FragmentGejala
import com.aditya.appsjeruk.user.ui.home.fragment.FragmentPenyakit

class MenuAdapter(fragment: HomeFragment) : FragmentStateAdapter(fragment) {


    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FragmentGejala()

            else -> FragmentPenyakit()
        }
    }
}