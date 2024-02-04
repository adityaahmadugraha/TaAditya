package com.aditya.appsjeruk.user.ui.diagnosa

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.appsjeruk.admin.AdminViewModel
import com.aditya.appsjeruk.data.Resource
import com.aditya.appsjeruk.data.remote.request.DiagnosaRequest
import com.aditya.appsjeruk.databinding.FragmentDiagnosaBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentDiagnosa : Fragment() {


    private var binding: FragmentDiagnosaBinding? = null
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

        mAdapter = AdapterDiagnosa { etSpinerValue ->
            Log.d("Diagnosa", "Nilai etSpiner: $etSpinerValue")
        }
        setupRecyclerView()

        binding?.btnDiagnosa?.setOnClickListener {
            val selectedSymptoms = mAdapter.getSelectedSymptoms()
            Log.d("Diagnosa", "Selected symptoms: $selectedSymptoms")

//    diagnosaPenyakit()
        }
    }


    private fun getData() {

        viewModel.getItem().observe(
            viewLifecycleOwner
        ) { result ->
            when (result) {
                is Resource.Loading -> {}

                is Resource.Success -> {

                    mAdapter.submitList(result.data)

                }

                is Resource.Error -> {}

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

