package com.aditya.appsjeruk.admin.adddata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.aditya.appsjeruk.R
import com.aditya.appsjeruk.admin.AdminViewModel
import com.aditya.appsjeruk.databinding.ActivityAddPenyakitBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ActivityAddPenyakit : AppCompatActivity() {

    private lateinit var binding: ActivityAddPenyakitBinding
    private val viewModel: AdminViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddPenyakitBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}