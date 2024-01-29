package com.aditya.appsjeruk.repository


import com.aditya.appsjeruk.data.local.UserLocal
import com.aditya.appsjeruk.data.local.UserPreference
import com.aditya.appsjeruk.data.remote.request.AddPenyakitRequest
import com.aditya.appsjeruk.data.remote.request.LoginRequest
import okhttp3.RequestBody
import javax.inject.Inject


class DataRepository @Inject constructor(
    private val remoteData: RemoteDataSource,
    private val localData: UserPreference
) {
    fun loginUser(request: LoginRequest) = remoteData.loginUser(request)


    fun isertData( requestBody: RequestBody) = remoteData.insertData(requestBody)
    fun getUser() = localData.getUser()
    suspend fun saveUser(userLocal: UserLocal) = localData.saveUser(userLocal)

    suspend fun deleteUser() = localData.deleteUser()

    fun getItem() = remoteData.getItem()




}