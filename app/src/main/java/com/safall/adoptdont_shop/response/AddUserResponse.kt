package com.safall.adoptdont_shop.response

import com.safall.adoptdont_shop.entity.User

data class AddUserResponse(
    val success: Boolean?=null,
    val data : User?= null
)
