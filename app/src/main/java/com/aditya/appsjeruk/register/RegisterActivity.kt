package com.aditya.appsjeruk.register

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.aditya.appsjeruk.R
import com.aditya.appsjeruk.data.Resource
import com.aditya.appsjeruk.data.remote.request.RegisterRequest
import com.aditya.appsjeruk.databinding.ActivityRegisterBinding
import com.aditya.appsjeruk.user.ui.login.LoginActivity
import com.aditya.appsjeruk.utils.Constant.isAllFieldsFilled
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvLogin.setOnClickListener {
            intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnRegister.setOnClickListener {
            registerUser()
        }

        playAnimation()
    }

    private fun registerUser() {
        val name = binding.etName.text.toString()
        val username = binding.etUsername.text.toString()
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        if (isAllFieldsFilled(name, username, email, password)) {
            viewModel.registerUser(RegisterRequest(name, username, email, password))
                .observe(this@RegisterActivity) { result ->
                    when (result) {
                        is Resource.Loading -> {
                            Log.d("RegisterActivity", "Loading...")
                            // Tambahkan logika atau tindakan yang ingin Anda lakukan saat pendaftaran sedang dilakukan
                        }

                        is Resource.Success -> {
                            if (result.data.status == true) {
                                Log.d("RegisterActivity", "Registration success")
                                Toast.makeText(
                                    this@RegisterActivity,
                                    "Akun Berhasil Register",
                                    Toast.LENGTH_SHORT
                                ).show()

                                val intent =
                                    Intent(this@RegisterActivity, LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Log.d(
                                    "RegisterActivity",
                                    "Registration failed: ${result.data.message}"
                                )
                                Toast.makeText(
                                    this@RegisterActivity,
                                    result.data.message,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        is Resource.Error -> {
                            Log.e("RegisterActivity", "Registration error: ${result.error}")
                            Toast.makeText(this@RegisterActivity, result.error, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
        } else {
            Log.d("RegisterActivity", "Fields are not filled")
            Toast.makeText(
                this@RegisterActivity,
                getString(R.string.must_not_empty),
                Toast.LENGTH_SHORT
            ).show()
        }
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
            val usernameLabel = ObjectAnimator.ofFloat(tvUsername, View.ALPHA, 1f).setDuration(150)
            val etUsername = ObjectAnimator.ofFloat(ilUsername, View.ALPHA, 1f).setDuration(150)
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
                    usernameLabel,
                    etUsername,
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