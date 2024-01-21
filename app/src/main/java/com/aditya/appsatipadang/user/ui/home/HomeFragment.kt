package com.aditya.appsatipadang.user.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.appsatipadang.adapter.AdapterLaporan
import com.aditya.appsatipadang.data.remote.response.ItemLaporaneResponse
import com.aditya.appsatipadang.databinding.FragmentHomeBinding
import com.aditya.appsatipadang.user.ui.detailstatuslaporan.DetailStatusLaporanActivity
import com.aditya.appsatipadang.user.ui.detailstatuslaporan.DetailStatusLaporanActivity.Companion.TAG_BUNDLE
import com.aditya.appsatipadang.user.ui.detailstatuslaporan.DetailStatusLaporanActivity.Companion.TAG_STATUS
import com.aditya.appsatipadang.user.ui.detailstatuslaporan.DetailStatusLaporanActivity.Companion.TAG_TANGGAL
import com.aditya.appsatipadang.user.ui.detailstatuslaporan.DetailStatusLaporanActivity.Companion.TAG_TIPE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var mAdapter: AdapterLaporan

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



//        getDataUser()
//        setupList()
    }

//    private fun getDataUser() {
//        viewModel.getUser().observe(viewLifecycleOwner) { userLocal ->
//            binding.tvName.text = userLocal.name
//
//
//            viewModel.getListLaporan(userLocal.getToken).observe(viewLifecycleOwner) { result ->
//                Log.d(TAG, "getDataUser: ${userLocal.getToken}")
//                when (result) {
//                    is Resource.Loading -> {
//                        binding.progressBar.isVisible = true
//                    }
//
//                    is Resource.Success -> {
//                        binding.progressBar.isVisible = false
//
//
//                        Log.d(TAG, "listadapter::::::: ${result.data}")
//
//
//                        val latestFiveData = if (result.data.laporan?.size!! > 5)
//                            result.data.laporan?.let {
//                                result.data.laporan.subList(
//                                    result.data.laporan.size - 5,
//                                    it.size
//                                )
//                            }
//                        else
//                            result.data.laporan
//
//                        mAdapter.submitListReversed(latestFiveData)
//                        setupRecyclerView()
//                    }
//
//                    is Resource.Error -> {
//                        binding.progressBar.isVisible = false
//                        Toast.makeText(
//                            requireActivity(),
//                            result.error,
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//
//                }
//            }
//
//        }
//    }


//    private fun setupList() {
//        mAdapter = AdapterLaporan {
//            goToDetailScreen(it)
//        }
//    }

    private fun goToDetailScreen(itemLaporaneResponse: ItemLaporaneResponse) {

        val bundle = Bundle().apply {


            putString(TAG_TIPE, itemLaporaneResponse.type)
            putString(TAG_TANGGAL, itemLaporaneResponse.tanggal)
//            putString(TAG_LOKASI, itemLaporaneResponse.lokasi)
            putString(TAG_STATUS, itemLaporaneResponse.status)


        }
        Intent(requireActivity(), DetailStatusLaporanActivity::class.java).apply {
            putExtra(TAG_BUNDLE, bundle)
            startActivity(this)
        }
    }

    private fun setupRecyclerView() {
        binding?.rvLaporanHome?.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireActivity())
            setHasFixedSize(true)
        }
    }
}