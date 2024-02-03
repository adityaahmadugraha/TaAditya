package com.aditya.appsjeruk.repository

import com.aditya.appsjeruk.data.Resource
import com.aditya.appsjeruk.data.remote.request.Login
import com.aditya.appsjeruk.data.remote.request.LoginRequest
import com.aditya.appsjeruk.data.remote.request.RegisterRequest
import com.aditya.appsjeruk.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.RequestBody
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    fun loginUser(request: LoginRequest) = flow {
        emit(Resource.Loading())
        val response = apiService.login(request)
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it.message ?: ""))
    }.flowOn(Dispatchers.IO)


    fun registerUser(request: RegisterRequest) = flow {
        emit(Resource.Loading())
        val response = apiService.register(request)
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it.message ?: ""))
    }.flowOn(Dispatchers.IO)


    fun getItem() = flow {
        emit(Resource.Loading())
        val response = apiService.getItem()
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it.message ?: ""))
    }.flowOn(Dispatchers.IO)


    fun getPenyakit() = flow {
        emit(Resource.Loading())
        val response = apiService.getItemPenyakit()
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it.message ?: ""))
    }.flowOn(Dispatchers.IO)


//    fun deletepenyakit(id: String, requestBody: RequestBody) = flow {
//        emit(Resource.Loading())
//        val response = apiService.deletePenyakit(id, requestBody)
//        emit(Resource.Success(response))
//    }.catch {
//        emit(Resource.Error(it.message ?: ""))
//    }.flowOn(Dispatchers.IO)


    fun deletepenyakit(id: String) = flow<Resource<Login>> {
        emit(Resource.Loading())
        val response = apiService.deletePenyakit(id)
        response.let {
            if (it.status) emit(Resource.Success(it))
            else emit(Resource.Error(it.message))
        }
    }.catch {
        emit(Resource.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)


    fun insertData(
        request: RequestBody
    ) = flow {
        emit(Resource.Loading())
        val response = apiService.insertData(request)
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it.message ?: ""))
    }.flowOn(Dispatchers.IO)


    fun insertGejala(
        request: RequestBody
    ) = flow {
        emit(Resource.Loading())
        val response = apiService.insertGejala(request)
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it.message ?: ""))
    }.flowOn(Dispatchers.IO)


}