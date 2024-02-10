package com.aditya.appsjeruk.user.ui.hasil

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.aditya.appsjeruk.databinding.ActivityHasilBinding
import com.aditya.appsjeruk.user.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActivityHasil : AppCompatActivity() {

    private lateinit var binding: ActivityHasilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHasilBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val namaPenyakit = intent.getStringExtra("nama_penyakit")
//        val nilaiCf = intent.getStringExtra("nilai_cf")
//        val hasilDiagnosa = "$namaPenyakit $nilaiCf"
//        binding.tvHasil.text = hasilDiagnosa
        val namaPenyakit = intent.getStringExtra("nama_penyakit")
        val nilaiCf = intent.getFloatExtra("nilai_cf", 0f)
        val formattedCf = "%.0f".format(nilaiCf * 100)
        val hasilDiagnosa = "$namaPenyakit $formattedCf%"

//        val formattedCf = "%.2f".format(nilaiCf * 100)
//        val hasilDiagnosa = "$namaPenyakit $formattedCf%"
        binding.tvHasil.text = hasilDiagnosa
        Log.d("ActivityHasil", "Nilai Cf: $nilaiCf")
        binding.icBack.setOnClickListener {
            intent = Intent(this@ActivityHasil, MainActivity::class.java)
            startActivity(intent)
        }
    }


}
