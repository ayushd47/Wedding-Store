package com.safall.adoptdont_shop.response

import com.safall.adoptdont_shop.entity.Pet

data class PetResponse(
        val success : Boolean? = null,
        val data: MutableList<Pet>? = null
)
