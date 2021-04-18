package com.safall.adoptdont_shop.api


import com.safall.adoptdont_shop.response.FavResponse
import retrofit2.Response
import retrofit2.http.*

interface FavAPI {
    @FormUrlEncoded
    @POST("favorite/insert/")
    suspend fun insertFav(
        @Header("Authorization") token:String,

        @Field("petId") petId:String
    ): Response<FavResponse>

    //retrieve Favorites
    @GET("favorite/show/")
    suspend fun retrieveFav(
        @Header("Authorization") token: String
    ):Response<FavResponse>

    //delete Favorites
    @DELETE("favorite/delete/{id}")
    suspend fun deleteFav(
        @Header("Authorization") token:String,
        @Path("id") id: String
    ):Response<FavResponse>
}