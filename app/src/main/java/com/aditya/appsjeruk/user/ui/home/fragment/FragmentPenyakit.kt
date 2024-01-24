package com.aditya.appsjeruk.user.ui.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.appsjeruk.R
import com.aditya.appsjeruk.adapter.AdapterGejala
import com.aditya.appsjeruk.admin.AdminViewModel
import com.aditya.appsjeruk.databinding.FragmentGejalaBinding
import com.aditya.appsjeruk.databinding.FragmentPenyakitBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentPenyakit : Fragment() {

    private var binding: FragmentPenyakitBinding? = null
    private val viewModel: AdminViewModel by viewModels()
    private lateinit var mAdapter: AdapterGejala

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPenyakitBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        getData()
        mAdapter = AdapterGejala()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding?.rvPenyakit?.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun getData() {

        viewModel.getItem().observe(
            viewLifecycleOwner
        ) { result ->
            when (result) {
                is com.aditya.appsjeruk.data.Resource.Loading -> {}

                is com.aditya.appsjeruk.data.Resource.Success -> {

                    mAdapter.submitList(result.data)
                }

                is com.aditya.appsjeruk.data.Resource.Error -> {}

                else -> {}
            }
        }
    }


}