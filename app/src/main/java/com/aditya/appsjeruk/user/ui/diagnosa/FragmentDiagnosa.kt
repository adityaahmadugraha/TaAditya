package com.aditya.appsjeruk.user.ui.diagnosa

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.appsjeruk.admin.AdminViewModel
import com.aditya.appsjeruk.data.Resource
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

        mAdapter = AdapterDiagnosa()
        setupRecyclerView()

        binding?.btnDiagnosa?.setOnClickListener {
            val selectedSymptoms = mAdapter.getSelectedSymptoms()

            if (selectedSymptoms.isNotEmpty()) {
                val selectedTingkatKepastian = mAdapter.getSelectedTingkatKepastian()

                // Variable untuk menyimpan nilai perhitungan hasil diagnosa
                var calculationResult = ""

                for ((index, gejala) in selectedSymptoms.withIndex()) {
                    val tingkatKepastian = selectedTingkatKepastian[index]

                    Log.d(
                        "Diagnosa",
                        "Gejala: ${gejala.kodeGejala}, Tingkat Kepastian: $tingkatKepastian"
                    )
                }

                // Memperoleh hasil diagnosa
                val hasilDiagnosa = mAdapter.diagnosaPenyakit()

                // Menambahkan kode P dan nilai perhitungan ke pesan toast
                calculationResult = "$hasilDiagnosa ${"%.4f".format(mAdapter.rule1Certainty)}"

                // Menampilkan pesan toast
                val toastMessage = "Tanaman Anda didiagnosa penyakit $calculationResult"
                Toast.makeText(requireContext(), toastMessage, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Pilih gejala terlebih dahulu", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }

    private fun getData() {
        viewModel.getItem().observe(
            viewLifecycleOwner
        ) {
            when (it) {
                is Resource.Loading -> {}

                is Resource.Success -> {
                    mAdapter.submitList(it.data)
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

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}


