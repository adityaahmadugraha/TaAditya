package com.aditya.appsjeruk.register

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.aditya.appsjeruk.databinding.ActivityRegisterBinding
import com.aditya.appsjeruk.user.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvLogin.setOnClickListener {
            intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        playAnimation()
    }

    private fun playAnimation() {
        binding.apply {
            ObjectAnimator.ofPropertyValuesHolder(
                binding.imageView,
                PropertyValuesHolder.ofFloat(View.SCALE_X, 0.5f, 1.0f),
                PropertyValuesHolder.ofFloat(View.SCALE_Y, 0.5f, 1.0f)
            ).apply {
                duration = 1500
                repeatCount = ObjectAnimator.INFINITE
                repeatMode = ObjectAnimator.REVERSE
            }.start()

            val login = ObjectAnimator.ofFloat(textView, View.ALPHA, 1f).setDuration(200)
            val nameLable = ObjectAnimator.ofFloat(textView5, View.ALPHA, 1f).setDuration(150)
            val etName = ObjectAnimator.ofFloat(ilName, View.ALPHA, 1f).setDuration(150)
            val emailLable = ObjectAnimator.ofFloat(textView2, View.ALPHA, 1f).setDuration(150)
            val etEmail = ObjectAnimator.ofFloat(ilEmail, View.ALPHA, 1f).setDuration(150)
            val passwordLable = ObjectAnimator.ofFloat(textView3, View.ALPHA, 1f).setDuration(150)
            val etPassword = ObjectAnimator.ofFloat(ilPassword, View.ALPHA, 1f).setDuration(150)
            val btnRegister = ObjectAnimator.ofFloat(btnRegister, View.ALPHA, 1f).setDuration(150)
            val dontHaveAccount = ObjectAnimator.ofFloat(textView4, View.ALPHA, 1f).setDuration(150)
            val registerLabel = ObjectAnimator.ofFloat(tvLogin, View.ALPHA, 1f).setDuration(150)

            AnimatorSet().apply {
                playSequentially(
                    login,
                    nameLable,
                    etName,
                    emailLable,
                    etEmail,
                    passwordLable,
                    etPassword,
                    btnRegister,
                    dontHaveAccount,
                    registerLabel
                )
                start()
            }
        }
    }
}