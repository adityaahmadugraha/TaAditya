package com.aditya.appsjeruk.admin.riwayat_admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.appsjeruk.R
import com.aditya.appsjeruk.admin.ActivityAdmin
import com.aditya.appsjeruk.admin.AdminViewModel
import com.aditya.appsjeruk.databinding.ActivityRiwayatAdminBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ActivityRiwayatAdmin : AppCompatActivity() {

    private lateinit var binding: ActivityRiwayatAdminBinding

    private val viewModel: AdminViewModel by viewModels()
    private lateinit var mAdapter : AdapterRiwayatAll
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRiwayatAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.icBack.setOnClickListener {
            intent = Intent(this@ActivityRiwayatAdmin, ActivityAdmin::class.java)
            startActivity(intent)

        }

        getRiwayat()
        mAdapter = AdapterRiwayatAll()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.rvRiwayatAdmin.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@ActivityRiwayatAdmin)
            setHasFixedSize(true)
        }
    }

    private fun getRiwayat() {
        viewModel.getRiwayatAll().observe(this@ActivityRiwayatAdmin){ result ->
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