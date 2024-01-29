package com.aditya.appsjeruk.user.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aditya.appsjeruk.R
import com.aditya.appsjeruk.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActivityDetail : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDetail()
    }

    private fun getDetail() {

        val title = intent.getStringExtra("title").toString()
        val image = intent.getStringExtra("image").toString()
        val deskripsi = intent.getStringExtra("deskripsi").toString()

        val tvDeskripsi = binding.tvName
        tvDeskripsi.text = title

        val tvName = binding.tvDeskripsi
        tvName.text = deskripsi
        Glide.with(this)
            .load(image)
            .into(binding.imgDetail)

    }
}