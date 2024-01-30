package com.aditya.appsjeruk.admin.riwayat_admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aditya.appsjeruk.R
import com.aditya.appsjeruk.admin.ActivityAdmin
import com.aditya.appsjeruk.databinding.ActivityRiwayatAdminBinding

class ActivityRiwayatAdmin : AppCompatActivity() {

    private lateinit var binding: ActivityRiwayatAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRiwayatAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.icBack.setOnClickListener {
            intent = Intent(this@ActivityRiwayatAdmin, ActivityAdmin::class.java)
            startActivity(intent)

        }
    }
}