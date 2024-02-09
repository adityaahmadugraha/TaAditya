package com.aditya.appsjeruk.user.ui.hasil

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aditya.appsjeruk.databinding.ActivityHasilBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ActivityHasil : AppCompatActivity() {

    private lateinit var binding: ActivityHasilBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHasilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hasilDiagnosa = intent.getStringExtra("hasil_diagnosa")
        binding.tvHasil.text = hasilDiagnosa


    }
}