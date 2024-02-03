package com.aditya.appsjeruk.user.detail_pengguna

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aditya.appsjeruk.R
import com.aditya.appsjeruk.databinding.ActivityDetailPenggunaBinding
import com.aditya.appsjeruk.user.MainActivity
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
        val image = intent.getStringExtra("image").toString()
        val deskripsi = intent.getStringExtra("deskripsi").toString()
        val kodeValue = intent.getStringExtra("kode").toString()
        val pencegahan = intent.getStringExtra("pencegahan").toString()


        val etPencegahan = binding.etPencegahan
        etPencegahan.setText(pencegahan)

        val etNama = binding.etNama
        etNama.setText(title)

        val etDeskripsi = binding.etDeskripsi
        etDeskripsi.setText(deskripsi)

//        val tvDeskripsi = binding.tvName
//        tvDeskripsi.text = title

//        val tvName = binding.tvDeskripsi
//        tvName.text = deskripsi

        val kodeToolbar = binding.kode
        kodeToolbar.title = kodeValue



        Glide.with(this)
            .load(image)
            .error(R.color.purple_200)
            .into(binding.imgDetail)
    }

}