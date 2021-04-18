package com.safall.adoptdont_shop.repository

import com.safall.adoptdont_shop.api.MyApiRequest
import com.safall.adoptdont_shop.api.ServiceBuilder
import com.safall.adoptdont_shop.api.SingleProductAPI
import com.safall.adoptdont_shop.response.ProductResponse

class SingleProductRepository: MyApiRequest() {
    private val singleProductAPI = ServiceBuilder.buildService(SingleProductAPI::class.java)

    //Display Single Product
    suspend fun getSingleProduct(id:String):ProductResponse{
        return apiRequest {
            singleProductAPI.showSingleProduct(id)
        }
    }
}