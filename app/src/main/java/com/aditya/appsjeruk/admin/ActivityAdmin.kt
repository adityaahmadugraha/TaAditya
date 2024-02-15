package com.aditya.appsjeruk.admin

import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.appsjeruk.R
import com.aditya.appsjeruk.adapter.AdapterGejala
import com.aditya.appsjeruk.admin.adddata.ActivityAddPenyakit
import com.aditya.appsjeruk.admin.addrule.ActivityAddRule
import com.aditya.appsjeruk.admin.addrule.AddGejalaActivity
import com.aditya.appsjeruk.admin.profile_amin.ActivityProfile
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
            cardPenyakit.setOnClickListener {
                intent = Intent(this@ActivityAdmin, ActivityAddPenyakit::class.java)
                startActivity(intent)

            }
            cardGejala.setOnClickListener {
                intent = Intent(this@ActivityAdmin, AddGejalaActivity::class.java)
                startActivity(intent)
            }
            cardRiwayat.setOnClickListener {
                intent = Intent(this@ActivityAdmin, ActivityRiwayatAdmin::class.java)
                startActivity(intent)
            }
            cardRule.setOnClickListener {
                intent = Intent(this@ActivityAdmin, ActivityAddRule::class.java)
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

        binding.icMenu.setOnClickListener {
            showPopupMenu()
        }
    }

    private fun showPopupMenu() {
        PopupMenu(this@ActivityAdmin, binding.icMenu).run {
            menuInflater.inflate(R.menu.popup_menu, menu)

            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menu_logout -> {
                        AlertDialog.Builder(this@ActivityAdmin)
                            .setTitle(R.string.attention)
                            .setMessage(R.string.alert_logout)
                            .setPositiveButton(R.string.yes) { _, _ ->
                                viewModel.deleteUser()
                                Intent(this@ActivityAdmin, LoginActivity::class.java).also { i ->
                                    i.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    startActivity(i)
                                }
                            }
                            .setNegativeButton(R.string.cancel) { dialog, _ ->
                                dialog.dismiss()
                            }
                            .show()
                    }

                    R.id.menu_profile -> {
                        startActivity(Intent(this@ActivityAdmin, ActivityProfile::class.java))
                    }
                }
                true
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                gravity = Gravity.END
            }
            show()
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