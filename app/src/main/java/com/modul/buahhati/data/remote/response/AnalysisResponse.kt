package com.modul.buahhati.data.remote.response

import com.google.gson.annotations.SerializedName

data class AnalysisResponse(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("headCircumference")
	val headCircumference: Int? = null,

	@field:SerializedName("child_id")
	val childId: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("weight")
	val weight: Int? = null,

	@field:SerializedName("age")
	val age: Int? = null,

	@field:SerializedName("height")
	val height: Int? = null
)
