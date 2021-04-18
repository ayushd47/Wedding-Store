package com.safall.adoptdont_shop.repository

import com.safall.adoptdont_shop.api.MyApiRequest
import com.safall.adoptdont_shop.api.ServiceBuilder
import com.safall.adoptdont_shop.api.SinglePetAPI

import com.safall.adoptdont_shop.response.PetResponse


class SinglePetRepository : MyApiRequest(){
    private val singlePetAPI = ServiceBuilder.buildService(SinglePetAPI::class.java)

    //Display Single Product
    suspend fun getSinglePet(id:String): PetResponse {
        return apiRequest {
            singlePetAPI.showSinglePet(id)
        }
    }
}