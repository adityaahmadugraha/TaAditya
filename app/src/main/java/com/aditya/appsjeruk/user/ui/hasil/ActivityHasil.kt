package com.aditya.appsjeruk.user.ui.hasil

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import androidx.appcompat.app.AppCompatActivity
import com.aditya.appsjeruk.databinding.ActivityHasilBinding
import com.aditya.appsjeruk.user.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class ActivityHasil : AppCompatActivity() {

    private lateinit var binding: ActivityHasilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHasilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val namaPenyakit = intent.getStringExtra("nama_penyakit")
        val nilaiCf = intent.getStringExtra("nilai_cf")
        val hasilDiagnosa = "$namaPenyakit $nilaiCf"
        binding.tvHasil.text = hasilDiagnosa

        binding.icBack.setOnClickListener {
            intent = Intent(this@ActivityHasil, MainActivity::class.java)
            startActivity(intent)
        }
    }


}
