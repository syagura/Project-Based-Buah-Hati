package com.modul.buahhati.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResultResponse(

	@field:SerializedName("data")
	val data: SingleResultData? = null,

	@field:SerializedName("status")
	val status: Boolean? = null,

	@field:SerializedName("statusCode")
	val statusCode: Int? = null
)

data class SingleResultData(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("child_id")
	val childId: String? = null,

	@field:SerializedName("headCircumference_age_gender")
	val headCircumferenceAgeGender: String? = null,

	@field:SerializedName("__v")
	val v: Int? = null,

	@field:SerializedName("weight_height")
	val weightHeight: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("height_age")
	val heightAge: String? = null,

	@field:SerializedName("analysis_id")
	val analysisId: String? = null,

	@field:SerializedName("weight_age")
	val weightAge: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null,

	@field:SerializedName("recomendation")
	val recomendation: String? = null
)