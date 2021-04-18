package com.safall.adoptdont_shop.api

import com.safall.adoptdont_shop.response.PetResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SinglePetAPI {
    //Single pet show
    @GET("/pet/singleshow/{id}")
    suspend fun showSinglePet(
            @Path("id")id:String
    ): Response<PetResponse>
}