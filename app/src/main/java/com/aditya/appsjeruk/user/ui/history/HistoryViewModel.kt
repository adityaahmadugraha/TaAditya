package com.aditya.appsjeruk.user.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.aditya.appsjeruk.data.local.UserLocal
import com.aditya.appsjeruk.data.local.UserPreference
import com.aditya.appsjeruk.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repository: DataRepository,
    private val userPreference: UserPreference
) : ViewModel() {

    fun getUser(
        user: (UserLocal) -> Unit
    ) = viewModelScope.launch {
        userPreference.getUser().collect {
            user(it)
        }
    }

    fun getRiwayatPengguna(
        id : String
    ) = repository.getRiwayatPengguna(id).asLiveData()


    fun deleteRiwayat(id_riwayat: String) = repository.deleteRiwayat(id_riwayat).asLiveData()
}