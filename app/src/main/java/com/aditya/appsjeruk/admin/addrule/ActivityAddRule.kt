package com.aditya.appsjeruk.admin.addrule

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aditya.appsjeruk.admin.ActivityAdmin
import com.aditya.appsjeruk.databinding.ActivityAddRuleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActivityAddRule : AppCompatActivity() {

    private lateinit var binding: ActivityAddRuleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddRuleBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.icBack.setOnClickListener {
            intent = Intent(this@ActivityAddRule, ActivityAdmin::class.java)
            startActivity(intent)
        }
    }
}