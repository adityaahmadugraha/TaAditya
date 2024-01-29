package com.aditya.appsjeruk.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aditya.appsjeruk.data.remote.request.RegisterRequest
import com.aditya.appsjeruk.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel()
{

    fun registerUser(request: RegisterRequest) = dataRepository.registerUser(request).asLiveData()

}