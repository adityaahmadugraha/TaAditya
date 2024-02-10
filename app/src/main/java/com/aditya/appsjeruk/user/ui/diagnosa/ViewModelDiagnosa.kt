package com.aditya.appsjeruk.user.ui.diagnosa

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.aditya.appsjeruk.data.local.UserLocal
import com.aditya.appsjeruk.data.local.UserPreference
import com.aditya.appsjeruk.data.remote.request.DiagnosaRequest
import com.aditya.appsjeruk.data.remote.request.UserData
import com.aditya.appsjeruk.repository.DataRepository
import com.aditya.appsjeruk.user.ui.history.RiwayatRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class ViewModelDiagnosa @Inject constructor(
    private val repository: DataRepository,
    private val userPreference: UserPreference,
) : ViewModel() {

    fun getUser(
        user: (UserLocal) -> Unit
    ) = viewModelScope.launch {
        userPreference.getUser().collect {
            user(it)
        }
    }

    fun insertRiwayat(
        request: RiwayatRequest
    ) = repository.insertRiwayat(request).asLiveData()

    fun getItem() = repository.getItem().asLiveData()


}