package com.aditya.appsjeruk.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.aditya.appsjeruk.R
import com.aditya.appsjeruk.databinding.ActivityAdminBinding
import com.aditya.appsjeruk.user.ui.login.LoginActivity
import com.aditya.appsjeruk.user.ui.profile.ProfileViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ActivityAdmin : AppCompatActivity() {

    private lateinit var binding: ActivityAdminBinding
    private val viewModel: AdminViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataUser()



        checkUserLogin()
        binding.apply {
            icMenu.setOnClickListener {
                showAlertLogout()
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
            binding.tvNama?.text = data.name


        }
    }
}