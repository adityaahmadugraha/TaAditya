package com.aditya.appsjeruk.admin.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.appsjeruk.adapter.AdapterGejala
import com.aditya.appsjeruk.admin.AdminViewModel
import com.aditya.appsjeruk.databinding.FragmentAdminGejalaBinding
import com.aditya.appsjeruk.user.ui.detail.ActivityDetail
import com.aditya.appsjeruk.user.ui.detail.ActivityDetailGejala
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentAdminGejala : Fragment() {

    private var binding: FragmentAdminGejalaBinding? = null
    private val viewModel: AdminViewModel by viewModels()
    private lateinit var mAdapter: AdapterGejala
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdminGejalaBinding.inflate(layoutInflater, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()
        mAdapter = AdapterGejala {
            val intent = Intent(requireContext(), ActivityDetailGejala::class.java).apply {
                putExtra("title", it.namaGejala)
                putExtra("deskripsi", it.deskripsiGejala)
//                putExtra("pencegahan", it.pencegahan)
                putExtra("image", it.fotoGejala)
                putExtra("id", it.idGejala)
//                it.putExtra("id", store.id)
            }
            startActivity(intent)

        }
        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        binding!!.rvGejala.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context)
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