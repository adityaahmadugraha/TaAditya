package com.aditya.appsjeruk.user.ui.diagnosa

import android.content.Intent
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
import com.aditya.appsjeruk.user.ui.hasil.ActivityHasil
import com.aditya.appsjeruk.user.ui.history.RiwayatRequest
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
        binding = FragmentDiagnosaBinding.inflate(inflater, container, false)
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

                for ((index, gejala) in selectedSymptoms.withIndex()) {
                    val tingkatKepastian = selectedTingkatKepastian[index]

                    Log.d(
                        "Diagnosa",
                        "Gejala: ${gejala.kodeGejala}, Tingkat Kepastian: $tingkatKepastian"
                    )
                }

                val hasilDiagnosa = mAdapter.diagnosaPenyakit()
                var namaPenyakit = ""
                var nilaiCf = ""



                if (hasilDiagnosa != "Tidak Diketahui") {
                    when (hasilDiagnosa) {
                        "P1", "P2", "P3", "P4", "P5", "P6" -> {
                            nilaiCf = "%.4f".format(mAdapter.hasilPerhitunganCf)
                            namaPenyakit = "Tanaman Anda didiagnosa penyakit $hasilDiagnosa"
                        }
                    }
                } else {
                    namaPenyakit = "Tidak diketahui penyakit"
                }

                val intent = Intent(requireContext(), ActivityHasil::class.java).apply {
                    putExtra("nama_penyakit", namaPenyakit)
                    putExtra("nilai_cf", nilaiCf.toFloat())
                    inputRiwayat(namaPenyakit, nilaiCf)
                }
                startActivity(intent)
            } else {
                Toast.makeText(requireContext(), "Pilih gejala terlebih dahulu", Toast.LENGTH_SHORT)
                    .show()
            }

        }


    }

    private fun inputRiwayat(namaPenyakit: String, nilaiCf: String) {
        Log.d("Data Riwayat", "Hasil Diagnosa: $namaPenyakit, Kode Penyakit: $nilaiCf")


        val request = RiwayatRequest(
            hasil_diagnosa = namaPenyakit,
            kode_penyakit = nilaiCf
        )



        viewModel.insertRiwayat(request).observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {}
                is Resource.Success -> {

                    if (result.data.status) {
                    } else {
                        Toast.makeText(
                            requireContext(),
                            result.data.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                is Resource.Error -> {
                    Toast.makeText(requireContext(), result.error, Toast.LENGTH_SHORT)
                        .show()
                }
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
