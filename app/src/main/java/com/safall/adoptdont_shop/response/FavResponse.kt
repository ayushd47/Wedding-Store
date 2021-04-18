package com.safall.adoptdont_shop.response


import com.safall.adoptdont_shop.entity.Favorite

data class FavResponse(
    val success: Boolean? = null,
    val data: MutableList<Favorite>? = null
)
