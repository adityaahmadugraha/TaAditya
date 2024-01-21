package com.aditya.appsjeruk.user.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aditya.appsjeruk.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    fun getUser() = repository.getUser().asLiveData()

//    fun getListLaporan(token : String) = repository.getListLaporan(token).asLiveData()

}