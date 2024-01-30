package com.aditya.appsjeruk.admin.addrule

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aditya.appsjeruk.admin.ActivityAdmin
import com.aditya.appsjeruk.databinding.ActivityAddRuleBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddRuleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddRuleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAddRuleBinding.inflate(layoutInflater)


        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.icBack.setOnClickListener {
            intent = Intent(this@AddRuleActivity, ActivityAdmin::class.java)
            startActivity(intent)
        }
    }
}