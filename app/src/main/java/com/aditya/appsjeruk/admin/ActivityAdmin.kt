package com.aditya.appsjeruk.admin

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.appsjeruk.R
import com.aditya.appsjeruk.adapter.AdapterGejala
import com.aditya.appsjeruk.admin.adddata.ActivityAddPenyakit
import com.aditya.appsjeruk.admin.addrule.AddGejalaActivity
import com.aditya.appsjeruk.admin.riwayat_admin.ActivityRiwayatAdmin
import com.aditya.appsjeruk.databinding.ActivityAdminBinding
import com.aditya.appsjeruk.user.ui.detail.ActivityDetail
import com.aditya.appsjeruk.user.ui.login.LoginActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ActivityAdmin : AppCompatActivity() {

    private lateinit var binding: ActivityAdminBinding
    private val viewModel: AdminViewModel by viewModels()
    private lateinit var mAdapter: AdapterGejala
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main_admin)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.fragmentAdminGejala, R.id.fragmentAdminPenyakit
            )
        )
   navView.setupWithNavController(navController)

        binding.apply {
            cardData.setOnClickListener {
                intent = Intent(this@ActivityAdmin, ActivityAddPenyakit::class.java)
                startActivity(intent)

            }
            cardRule.setOnClickListener {
                intent = Intent(this@ActivityAdmin, AddGejalaActivity::class.java)
                startActivity(intent)
            }
            cardRiwayat.setOnClickListener {
                intent = Intent(this@ActivityAdmin, ActivityRiwayatAdmin::class.java)
                startActivity(intent)
            }
        }



        getDataUser()
        getData()
        mAdapter = AdapterGejala {
            intent = Intent(this@ActivityAdmin, ActivityDetail::class.java).apply {
                putExtra("title", it.namaGejala)
                putExtra("deskripsi", it.deskripsiGejala)
//                putExtra("pencegahan", it.pencegahan)
                putExtra("image", it.fotoGejala)
                putExtra("id", it.idGejala)
//                it.putExtra("id", store.id)
            }
            startActivity(intent)

        }

//        setupRecyclerView()


        checkUserLogin()
        binding.apply {
            icMenu.setOnClickListener {
                showAlertLogout()
            }
        }
    }

//    private fun setupRecyclerView() {
//        binding.rvPenyakit.apply {
//            adapter = mAdapter
//            layoutManager = LinearLayoutManager(this@ActivityAdmin)
//            setHasFixedSize(true)
//        }
//    }

    private fun getData() {

        viewModel.getItem().observe(
            this@ActivityAdmin
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

    private fun showAlertLogout() {
        MaterialAlertDialogBuilder(this)
            .setMessage(getString(R.string.logoutMessage))
            .setPositiveButton(getString(R.string.yes)) { dialog, _ ->
                viewModel.deleteUser()
                checkUserLogin()
            }
            .setNegativeButton(getString(R.string.no)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }


    private fun checkUserLogin() {
        viewModel.getUser().observe(this@ActivityAdmin) {
            if (it.username.isEmpty()) {
                Intent(this@ActivityAdmin, LoginActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(this)
                }
            }
        }
    }

    private fun getDataUser() {
        viewModel.getUser().observe(this@ActivityAdmin) { data ->
            binding.tvNama.text = data.name


        }
    }
}