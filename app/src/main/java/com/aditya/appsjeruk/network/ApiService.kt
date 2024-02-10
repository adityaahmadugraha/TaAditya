package com.aditya.appsjeruk.network

import com.aditya.appsjeruk.data.remote.request.DiagnosaRequest
import com.aditya.appsjeruk.data.remote.request.DiagnosaResponse
import com.aditya.appsjeruk.data.remote.request.Login
import com.aditya.appsjeruk.data.remote.request.LoginRequest
import com.aditya.appsjeruk.data.remote.request.RegisterRequest
import com.aditya.appsjeruk.data.remote.response.AddPenyakitResponse
import com.aditya.appsjeruk.user.ui.diagnosa.GejalaResponse
import com.aditya.appsjeruk.data.remote.response.PenyakitResponse
import com.aditya.appsjeruk.data.remote.response.RegisterResponse
import com.aditya.appsjeruk.user.ui.history.Riwayat
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("login.php")
    suspend fun login(@Body request: LoginRequest): Login

    @POST("register.php")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse


//    @GET("get_penyakit.php")
//    suspend fun getItem(): List<PenyakitResponse>


    @GET("get_gejala.php")
    suspend fun getItem(): List<GejalaResponse>


    @GET("get_penyakit.php")
    suspend fun getItemPenyakit(): List<PenyakitResponse>


    @GET("get_riwayat_pengguna.php")
    suspend fun getRiwayatPengguna(): List<Riwayat>

//    @POST("create_penyakit.php")
//    suspend fun insertData(@Body body: RequestBody): AddPenyakitResponse

    @POST("input_penyakit.php")
    suspend fun insertData(@Body body: RequestBody): AddPenyakitResponse


    @POST("input_gejala.php")
    suspend fun insertGejala(
        @Body body: RequestBody
    ): AddPenyakitResponse


    @GET("delete_penyakit.php")
    suspend fun deletePenyakit(
        @Query("id") id: String
    ): Login

    @POST("update_user.php")
    suspend fun updateUser(
        @Query("id_user") id: String
    ): Login


    @POST("rule.php")
    suspend fun diagnosaPenyakit(
        @Body request: DiagnosaRequest
    ): DiagnosaResponse


}