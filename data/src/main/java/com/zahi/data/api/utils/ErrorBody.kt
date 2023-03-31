package com.zahi.data.api.utils

data class ErrorBody(
    val statusCode: Int,
    val error: String,
    val message: String,
)
