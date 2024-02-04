package com.aditya.appsjeruk.data.remote.request

data class DiagnosaRequest(
    val gejala: List<String>,
    val tingkatKepastian: List<Double>
)

data class DiagnosaResponse(
    val namaPenyakit: String?,

)

