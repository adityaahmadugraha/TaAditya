package com.aditya.appsjeruk.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aditya.appsjeruk.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ActivityAdmin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
    }
}