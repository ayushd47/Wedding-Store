package com.safall.adoptdont_shop.response


import com.safall.adoptdont_shop.entity.User

data class LoginResponse(
    val success :Boolean? = null,
    val token : String? = null,
    val data: User? = null
)
