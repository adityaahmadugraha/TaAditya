package com.aditya.appsjeruk.admin.adddata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aditya.appsjeruk.R
import com.aditya.appsjeruk.databinding.ActivityAddPenyakitBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ActivityAddPenyakit : AppCompatActivity() {

    private lateinit var binding: ActivityAddPenyakitBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddPenyakitBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}