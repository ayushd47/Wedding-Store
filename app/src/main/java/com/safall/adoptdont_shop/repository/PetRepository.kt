package com.safall.adoptdont_shop.repository

import android.util.Log
import com.safall.adoptdont_shop.api.MyApiRequest
import com.safall.adoptdont_shop.api.PetAPI
import com.safall.adoptdont_shop.api.ServiceBuilder
import com.safall.adoptdont_shop.dao.PetDAO
import com.safall.adoptdont_shop.entity.Pet


class PetRepository(private val petDao: PetDAO): MyApiRequest() {

    private val petAPI = ServiceBuilder.buildService(PetAPI::class.java)
//    suspend fun getAllPets(): PetResponse {
//        return apiRequest {
//            petAPI.getAllPets()
//        }
//    }
    suspend fun displayAllPets() : MutableList<Pet>?{
    try {
        val response = apiRequest{petAPI.getAllPets()}
        saveInRoom(response.data!!)
        return petDao.getAllPets()
    }
    catch(ex:Exception){
        Log.d("repo",ex.toString())
    }
    return petDao.getAllPets()
}

    private suspend fun saveInRoom(pets: MutableList<Pet>){
        for (pet in pets){
            petDao.insertPet(pet)
        }
    }
}