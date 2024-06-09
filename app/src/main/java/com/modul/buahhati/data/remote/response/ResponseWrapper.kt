package com.modul.buahhati.data.remote.response

data class ResponseWrapper<T>(
    val status: Boolean,
    val statusCode: Int,
    val data: T
)
