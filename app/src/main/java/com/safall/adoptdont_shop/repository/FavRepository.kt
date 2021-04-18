package com.safall.adoptdont_shop.repository

import com.safall.adoptdont_shop.api.CartAPI
import com.safall.adoptdont_shop.api.FavAPI
import com.safall.adoptdont_shop.api.MyApiRequest
import com.safall.adoptdont_shop.api.ServiceBuilder
import com.safall.adoptdont_shop.response.CartResponse
import com.safall.adoptdont_shop.response.FavResponse

class FavRepository(): MyApiRequest()  {
    private val favAPI= ServiceBuilder.buildService(FavAPI::class.java)

    //Insert Product To cart
    suspend fun addTofav(petId:String,token:String): FavResponse {
        return apiRequest {
            favAPI.insertFav(token!!,petId)
        }
    }

    //Retrieve cart
    suspend fun getFav(token: String): FavResponse {
        return apiRequest {
            favAPI.retrieveFav(token!!)
        }
    }

//    ///UpdateCart
//    suspend fun updateCart(token:String,id: String,quantity:Int):CartResponse{
//        return apiRequest {
//            cartAPI.updateCart(token!!,id,quantity)
//        }
//    }

    ///DeleteCart
    suspend fun deleteFav(id: String,token:String): FavResponse {
        return apiRequest {
            favAPI.deleteFav(token!!,id)
        }
    }
}