package com.aditya.appsjeruk.user.ui.history

data class Riwayat(
    val id_riwayat: String,
    val id_user: Int?,
    val id_penyakit: Int?,
    val kode_penyakit: String?,
    val pencegahan: String?,
    val hasil_diagnosa: String?,
    val nama_penyakit: String?,
    val tgl_diagnosa: String?,
    val nama_user: String?
)
