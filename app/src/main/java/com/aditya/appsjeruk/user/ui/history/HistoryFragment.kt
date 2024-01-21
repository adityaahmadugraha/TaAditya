package com.aditya.appsjeruk.user.ui.history

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.aditya.appsjeruk.databinding.FragmentHistoryBinding
import com.aditya.appsjeruk.user.ui.detailstatuslaporan.DetailStatusLaporanActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val viewModel: HistoryViewModel by viewModels()
    private val binding get() = _binding!!

//    private lateinit var mAdapter: AdapterHistoryLaporan

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        setupRecyclerView()
//        getDataUser()
    }




//    private fun setupRecyclerView() {
//        mAdapter = AdapterHistoryLaporan {
//            goToDetailScreen(it)
//        }
//    }

//    private fun goToDetailScreen(itemLaporaneResponse: ItemLaporaneResponse) {
//
//        val bundle = Bundle().apply {
//            putString(DetailStatusLaporanActivity.TAG_TIPE, itemLaporaneResponse.type)
//            putString(DetailStatusLaporanActivity.TAG_TANGGAL, itemLaporaneResponse.tanggal)
//            putString(DetailStatusLaporanActivity.TAG_LOKASI, itemLaporaneResponse.lokasi)
//
//
//        }
//        Intent(requireActivity(), DetailStatusLaporanActivity::class.java).apply {
//            putExtra(DetailStatusLaporanActivity.TAG_BUNDLE, bundle)
//            startActivity(this)
//        }
//    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}













