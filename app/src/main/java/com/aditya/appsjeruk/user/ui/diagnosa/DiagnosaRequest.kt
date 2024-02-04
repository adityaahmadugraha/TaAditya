package com.aditya.appsjeruk.user.ui.diagnosa

data class DiagnosaRequest(
    val gejala: List<String>,
    val tingkatKepastian: List<Double>
)
