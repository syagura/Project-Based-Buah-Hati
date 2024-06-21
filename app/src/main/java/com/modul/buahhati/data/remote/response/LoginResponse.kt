package com.modul.buahhati.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("status")
	val status: Boolean? = null,

	@field:SerializedName("statusCode")
	val statusCode: Int? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("user")
	val user: String? = null,

	@field:SerializedName("data")
	val data: Data? = null
)

data class Data(

	@field:SerializedName("accessToken")
	val accessToken: String? = null,

	@field:SerializedName("refreshToken")
	val refreshToken: String? = null
)
