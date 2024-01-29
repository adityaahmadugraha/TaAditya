package com.aditya.appsjeruk.user.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.aditya.appsjeruk.data.remote.request.LoginRequest
import com.aditya.appsjeruk.data.local.UserLocal
import com.aditya.appsjeruk.data.remote.request.AddPenyakitRequest
import com.aditya.appsjeruk.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel() {
    fun loginUser(loginRequest: LoginRequest) = dataRepository.loginUser(loginRequest).asLiveData()


  fun getUser() = dataRepository.getUser().asLiveData()

    fun saveUserLocal(userLocal: UserLocal) = viewModelScope.launch {
        dataRepository.saveUser(userLocal)
    }
}
