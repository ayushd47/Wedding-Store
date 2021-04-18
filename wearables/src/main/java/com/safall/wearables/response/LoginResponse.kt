package com.safall.wearables.response

data class LoginResponse(
    val success :Boolean? = null,
    val token : String? = null,
    val data: MutableList<User>? = null
)
