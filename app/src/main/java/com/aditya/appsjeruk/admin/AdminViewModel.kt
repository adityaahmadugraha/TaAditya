package com.aditya.appsjeruk.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.aditya.appsjeruk.data.Resource
import com.aditya.appsjeruk.data.remote.request.AddPenyakitRequest
import com.aditya.appsjeruk.data.remote.request.DiagnosaRequest
import com.aditya.appsjeruk.data.remote.request.DiagnosaResponse
import com.aditya.appsjeruk.repository.DataRepository
import com.aditya.appsjeruk.user.ui.history.Riwayat
import com.aditya.appsjeruk.user.ui.history.RiwayatRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import javax.inject.Inject


@HiltViewModel
class AdminViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    fun getUser() = repository.getUser().asLiveData()

    fun deleteUser() = viewModelScope.launch {
        repository.deleteUser()
    }

    fun insertPenyakit(
        requestBody: RequestBody
    ) = repository.isertData(requestBody).asLiveData()


    fun insertGejala(
        requestBody: RequestBody
    ) = repository.insertGejala(requestBody).asLiveData()


    fun insertRiwayat(
        request: RiwayatRequest
    ) = repository.insertRiwayat(request).asLiveData()

    fun getItem() = repository.getItem().asLiveData()
    fun getRiwayatAll() = repository.getRiwayatAll().asLiveData()
    fun getPenyakit() = repository.getPenyakit().asLiveData()


    fun deletePenyakit(id: String) = repository.deletePenyakit(id).asLiveData()
//    fun deleteRiwayat(id_riwayat: String) = repository.deleteRiwayat(id_riwayat).asLiveData()

    fun diagnosaPenyakit(request: DiagnosaRequest) =
        repository.diagnosaPenyakit(request).asLiveData()

//    }

}