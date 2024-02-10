package com.aditya.appsjeruk.user.ui.history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.appsjeruk.adapter.AdapterPenyakit
import com.aditya.appsjeruk.data.local.UserLocal
import com.aditya.appsjeruk.databinding.FragmentHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val viewModel: HistoryViewModel by viewModels()
    private lateinit var mAdapter: AdapterRiwayat
    private var user: UserLocal? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUser {
            user = it
            getRiwayat()
        }


        mAdapter = AdapterRiwayat ()
        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        binding.rvRiwayatPenggua.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun getRiwayat() {
        user?.let { currentUser ->
            viewModel.getRiwayatPengguna(currentUser.id).observe(
                viewLifecycleOwner
            ) { result ->
                when (result) {
                    is com.aditya.appsjeruk.data.Resource.Loading -> {
                        // Tambahkan log jika ada loading data
                        Log.d("HistoryFragment", "Loading riwayat pengguna...")
                    }

                    is com.aditya.appsjeruk.data.Resource.Success -> {
                        // Tambahkan log untuk jumlah data yang diterima
                        Log.d(
                            "HistoryFragment",
                            "Jumlah data riwayat pengguna: ${result.data.size}"
                        )
                        mAdapter.submitList(result.data)
                    }

                    is com.aditya.appsjeruk.data.Resource.Error -> {
                        // Tambahkan log jika terjadi kesalahan
                        Log.e("HistoryFragment", "Terjadi kesalahan: ${result.error}")
                    }

                    else -> {
                        // Tambahkan log jika kondisi lain terjadi
                        Log.d("HistoryFragment", "Kondisi lain terjadi")
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}













