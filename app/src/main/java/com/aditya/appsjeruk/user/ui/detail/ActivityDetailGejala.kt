package com.aditya.appsjeruk.user.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aditya.appsjeruk.BuildConfig
import com.aditya.appsjeruk.R
import com.aditya.appsjeruk.databinding.ActivityDetailGejalaBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActivityDetailGejala : AppCompatActivity() {

    private lateinit var binding: ActivityDetailGejalaBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailGejalaBinding.inflate(layoutInflater)
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
//        val pencegahan = intent.getStringExtra("pencegahan").toString()

//
//        val etPencegahan = binding.etPencegahan
//        etPencegahan.setText(pencegahan)

        val etNama = binding.etNama
        etNama.text = title

        val etDeskripsi = binding.etDeskripsi
        etDeskripsi.text = deskripsi

//        val tvDeskripsi = binding.tvName
//        tvDeskripsi.text = title

//        val tvName = binding.tvDeskripsi
//        tvName.text = deskripsi

//        val kodeToolbar = binding.kode
//        kodeToolbar.title = kodeValue



        Glide.with(this)
            .load(BuildConfig.IMAGE_URL_GEJALA + foto)
            .error(R.drawable.error)
            .into(binding.imgDetail)
    }

}