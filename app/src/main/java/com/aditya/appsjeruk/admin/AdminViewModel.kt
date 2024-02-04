package com.aditya.appsjeruk.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.aditya.appsjeruk.data.Resource
import com.aditya.appsjeruk.data.remote.request.AddPenyakitRequest
import com.aditya.appsjeruk.data.remote.request.DiagnosaRequest
import com.aditya.appsjeruk.data.remote.request.DiagnosaResponse
import com.aditya.appsjeruk.repository.DataRepository
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


    fun getItem() = repository.getItem().asLiveData()
    fun getPenyakit() = repository.getPenyakit().asLiveData()


    fun deletePenyakit(id: String) = repository.deletePenyakit(id).asLiveData()

    //    fun diagnosaPenyakit(request: DiagnosaRequest):
//            Flow<Resource<DiagnosaResponse>> {
//        return repository.diagnosaPenyakit(request)
    fun diagnosaPenyakit(request: DiagnosaRequest) = repository.diagnosaPenyakit(request).asLiveData()

//    }

}