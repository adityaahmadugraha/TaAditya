package com.aditya.appsjeruk.user.ui.history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
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



        mAdapter = AdapterRiwayat()
        mAdapter.setOnDeleteClickListener(object : AdapterRiwayat.OnDeleteClickListener {
            override fun onDeleteClick(riwayat: Riwayat) {
                showDeleteConfirmationDialog(riwayat.id_riwayat)
            }

            private fun showDeleteConfirmationDialog(idRiwayat: String) {
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("Konfirmasi")
                builder.setMessage("Apakah Anda yakin ingin menghapus riwayat ini?")
                builder.setPositiveButton("Ya") { dialog, _ ->
                    viewModel.deleteRiwayat(idRiwayat).observe(viewLifecycleOwner) { result ->
                        when (result) {
                            is com.aditya.appsjeruk.data.Resource.Success -> {
                                Log.d("HistoryFragment", "Riwayat berhasil dihapus")
                                mAdapter.currentList.toMutableList().removeAll { it.id_riwayat == idRiwayat }
                                mAdapter.notifyDataSetChanged()
                                showToast("Riwayat berhasil dihapus")
                            }
                            // Penanganan Error
                            is com.aditya.appsjeruk.data.Resource.Error -> {
                                Log.e("HistoryFragment", "Gagal menghapus riwayat: ${result.error}")
                                showToast("Gagal menghapus riwayat: ${result.error}")
                            }
                            else -> { }
                        }
                    }

                }

                builder.setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                }
                val dialog = builder.create()
                dialog.show()
            }


            private fun showToast(message: String) {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }

        })

        setupRecyclerView()

        viewModel.getUser {
            user = it
            getRiwayat()
        }
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













