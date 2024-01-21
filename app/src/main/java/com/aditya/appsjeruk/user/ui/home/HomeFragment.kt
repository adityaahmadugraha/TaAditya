package com.aditya.appsjeruk.user.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.aditya.appsjeruk.R
import com.aditya.appsjeruk.adapter.MenuAdapter
import com.aditya.appsjeruk.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val tabTitle = mutableMapOf(
        "Gejala" to R.string.gejala,
        "Penyakit" to R.string.penyakit

    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpTabLayoutWithViewPager()

    }

    private fun setUpTabLayoutWithViewPager() {
        val titles = ArrayList(tabTitle.keys)
        binding.viewPager.adapter = MenuAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()

        titles.forEachIndexed { index, title ->
            val tabView = LayoutInflater.from(context).inflate(R.layout.tab_title, null)
            val textView = tabView.findViewById<TextView>(R.id.text1)

            textView.text = title

            binding.tabLayout.getTabAt(index)?.customView = tabView
        }


    }

}