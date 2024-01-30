package com.aditya.appsjeruk.user.ui.diagnosa

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
import com.aditya.appsjeruk.databinding.FragmentDiagnosaBinding
import com.aditya.appsjeruk.databinding.FragmentPenyakitBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentDiagnosa : Fragment() {


    private var binding : FragmentDiagnosaBinding? = null
    private val viewModel: AdminViewModel by viewModels()
    private lateinit var mAdapter: AdapterDiagnosa

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDiagnosaBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()
        mAdapter = AdapterDiagnosa{}
        setupRecyclerView()
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

    private fun setupRecyclerView() {
        binding?.rvDiagnosa?.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

    }

}