package com.aditya.appsjeruk.network

import com.aditya.appsjeruk.data.remote.request.AddPenyakitRequest
import com.aditya.appsjeruk.data.remote.request.Login
import com.aditya.appsjeruk.data.remote.request.LoginRequest
import com.aditya.appsjeruk.data.remote.response.AddPenyakitResponse
import com.aditya.appsjeruk.data.remote.response.PenyakitResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("login.php")
    suspend fun login(@Body request: LoginRequest): Login


    @GET("get_penyakit.php")
    suspend fun getItem(): List<PenyakitResponse>

    @POST("create_penyakit.php")
    suspend fun insertData(@Body body: RequestBody): AddPenyakitRequest





}