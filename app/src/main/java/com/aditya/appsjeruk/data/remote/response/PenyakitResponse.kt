package com.aditya.appsjeruk.data.remote.response

import com.google.gson.annotations.SerializedName

data class PenyakitResponse(

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("foto")
	val foto: String? = null,

	@field:SerializedName("kode")
	val kode: String? = null,

	@field:SerializedName("deskripsi")
	val deskripsi: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("id_penyakit")
	val idPenyakit: Int? = null,

	@field:SerializedName("pencegahan")
	val pencegahan: String? = null
)
