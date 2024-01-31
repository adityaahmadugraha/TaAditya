package com.aditya.appsjeruk.admin.profile_amin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aditya.appsjeruk.databinding.ActivityProfileBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ActivityProfile : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}