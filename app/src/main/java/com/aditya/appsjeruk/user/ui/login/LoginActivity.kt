package com.aditya.appsjeruk.user.ui.login


import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.aditya.appsjeruk.admin.ActivityAdmin
import com.aditya.appsjeruk.data.Resource
import com.aditya.appsjeruk.data.local.UserLocal
import com.aditya.appsjeruk.data.remote.request.LoginRequest
import com.aditya.appsjeruk.databinding.ActivityLoginBinding
import com.aditya.appsjeruk.register.RegisterActivity
import com.aditya.appsjeruk.user.MainActivity
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        checkUserLogin()

        binding.btnLogin.setOnClickListener {
            loginUser()
        }

        binding.tvRegister.setOnClickListener {
            intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser() {
        val username = binding.txtUsername.text.toString()
        val password = binding.txtPassword.text.toString()

        Log.d("LoginActivity", "Username: $username, Password: $password")

        viewModel.loginUser(LoginRequest(username, password))
            .observe(this@LoginActivity) { result ->
                when (result) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE

                        Log.d("LoginActivity", "Loading...")
                    }

                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        if (result.data.status) {
                            val userData = result.data.data
                            viewModel.saveUserLocal(
                                UserLocal(
                                    userData.id,
                                    userData.nama,
                                    userData.username,
                                    userData.roles,
                                )
                            )
                            checkUserLogin()
                            Log.d("LoginActivity", "Login success. User data: $userData")
                        } else {
                            Log.d(
                                "LoginActivity",
                                "Unexpected response status: ${result.data.status}"
                            )
                        }
                    }

                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this@LoginActivity, result.error, Toast.LENGTH_SHORT).show()
                        Log.e("LoginActivity", "Login error: ${result.error}")
                    }
                }
            }
    }


    private fun checkUserLogin() {
        viewModel.getUser().observe(this@LoginActivity) { userData ->
            if
                    (userData.username.isNotEmpty() || userData.id.isNotEmpty()) {

                if (userData.roles == "Admin") {
                    Toast.makeText(this@LoginActivity, "Anda Berhasil Login", Toast.LENGTH_SHORT)
                        .show()
                    Log.d("LOGINADMIN:::::", userData.id)
                    Intent(this@LoginActivity, ActivityAdmin::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(this)
                    }
                } else if (userData.roles == "Pengguna") {
                    Toast.makeText(this@LoginActivity, "Anda Berhasil Login", Toast.LENGTH_SHORT)
                        .show()
                    Log.d("LOGIN:::::", userData.id)
                    Intent(this@LoginActivity, MainActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(this)
                    }
                }
            }

        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is TextInputEditText || v is AutoCompleteTextView) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                    v.clearFocus()
                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

}