package com.safall.adoptdont_shop.api

import com.safall.adoptdont_shop.response.ImageResponse
import com.safall.adoptdont_shop.response.PetResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface PetAPI {
    @GET("pet/show")
    suspend fun getAllPets(
//            @Header("Authorization") token:String,
    ) : Response<PetResponse>

    @Multipart
    @PUT("pet/{id}/photo")
    suspend fun uploadImage(
            @Header("Authorization") token: String,
            @Path("id") id: String,
            @Part file: MultipartBody.Part
    ): Response<ImageResponse>
}