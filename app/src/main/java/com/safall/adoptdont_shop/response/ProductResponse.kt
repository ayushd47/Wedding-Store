package com.safall.adoptdont_shop.response


import com.safall.adoptdont_shop.entity.Product

data class ProductResponse(
        val success : Boolean? = null,
        val data: MutableList<Product>? = null
)
