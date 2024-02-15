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
import com.aditya.appsjeruk.adapter.AdapterPenyakit
import com.aditya.appsjeruk.admin.AdminViewModel
import com.aditya.appsjeruk.databinding.FragmentAdminPenyakitBinding
import com.aditya.appsjeruk.databinding.FragmentPenyakitBinding
import com.aditya.appsjeruk.user.detail_pengguna.ActivityDetailPengguna
import com.aditya.appsjeruk.user.ui.detail.ActivityDetail
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentAdminPenyakit : Fragment() {

    private var binding: FragmentAdminPenyakitBinding? = null
    private val viewModel: AdminViewModel by viewModels()
    private lateinit var mAdapter: AdapterPenyakit
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAdminPenyakitBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        getData()
        mAdapter = AdapterPenyakit {
            val intent = Intent(requireContext(), ActivityDetail::class.java).apply {
                putExtra("title", it.namaPenyakit)
                putExtra("deskripsi", it.deskripsiPenyakit)
                putExtra("foto", it.fotoPenyakit)
                putExtra("kode", it.kodePenyakit)
                putExtra("id_penyakit", it.idPenyakit)
                putExtra("pencegahan", it.pencegahan)
            }
            startActivity(intent)
        }
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

        viewModel.getPenyakit().observe(
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