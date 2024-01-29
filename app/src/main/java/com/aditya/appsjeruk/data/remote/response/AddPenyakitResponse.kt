package com.aditya.appsjeruk.data.remote.response

import com.google.gson.annotations.SerializedName

data class AddPenyakitResponse(

	@field:SerializedName("report_id")
	val reportId: Int? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)
