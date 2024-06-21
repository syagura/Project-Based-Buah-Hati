package com.modul.buahhati.data.remote.response

import com.google.gson.annotations.SerializedName

data class ChildRegisterResponse(

	@field:SerializedName("user_id")
	val userId: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("birthdate")
	val birthdate: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("blood_type")
	val bloodType: String? = null,

	@field:SerializedName("body_height")
	val bodyHeight: Int? = null,

	@field:SerializedName("head_circumference")
	val headCircumference: Int? = null,

	@field:SerializedName("body_weight")
	val bodyWeight: Int? = null
)
