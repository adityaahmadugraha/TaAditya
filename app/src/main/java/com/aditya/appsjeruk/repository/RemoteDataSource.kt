package com.aditya.appsjeruk.repository

import com.aditya.appsjeruk.data.Resource
import com.aditya.appsjeruk.data.remote.request.AddPenyakitRequest
import com.aditya.appsjeruk.data.remote.request.LoginRequest
import com.aditya.appsjeruk.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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


    fun getItem() = flow {
        emit(Resource.Loading())
        val response = apiService.getItem()
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it.message ?: ""))
    }.flowOn(Dispatchers.IO)



    fun insertData(request: AddPenyakitRequest) = flow {
        emit(Resource.Loading())
        val response = apiService.insertData(request)
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it.message ?: ""))
    }.flowOn(Dispatchers.IO)




}