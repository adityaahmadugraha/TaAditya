package com.aditya.appsjeruk.user.detail_pengguna

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aditya.appsjeruk.BuildConfig
import com.aditya.appsjeruk.R
import com.aditya.appsjeruk.databinding.ActivityDetailPenggunaBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActivityDetailPengguna : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPenggunaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailPenggunaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        getDetail()
    }
    private fun getDetail() {
        val title = intent.getStringExtra("title").toString()
        val foto = intent.getStringExtra("foto").toString()
        val deskripsi = intent.getStringExtra("deskripsi").toString()
        val kodeValue = intent.getStringExtra("kode").toString()
        val pencegahan = intent.getStringExtra("pencegahan").toString()

        val etPencegahan = binding.etPencegahan
        etPencegahan.setText(pencegahan)

        val etNama = binding.etNama
        etNama.setText(title)

        val etDeskripsi = binding.etDeskripsi
        etDeskripsi.setText(deskripsi)

        val kodeToolbar = binding.kode
        kodeToolbar.title = kodeValue

        Glide.with(this)
            .load(BuildConfig.IMAGE_URL + foto)
            .error(R.drawable.error)
            .into(binding.imgDetail)
    }

}