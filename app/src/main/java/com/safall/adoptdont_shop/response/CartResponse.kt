package com.safall.adoptdont_shop.response

import com.safall.adoptdont_shop.entity.Cart

data class CartResponse (
    val success: Boolean? = null,
    val data: MutableList<Cart>? = null
)