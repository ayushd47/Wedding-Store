package com.safall.adoptdont_shop.repository

import android.util.Log
import com.safall.adoptdont_shop.api.MyApiRequest
import com.safall.adoptdont_shop.api.ProductAPI
import com.safall.adoptdont_shop.api.ServiceBuilder
import com.safall.adoptdont_shop.dao.ProductDAO
import com.safall.adoptdont_shop.entity.Product


class ProductRepository (private val productDao: ProductDAO): MyApiRequest() {
    private val productAPI = ServiceBuilder.buildService(ProductAPI::class.java)
//    suspend fun getAllProducts(): ProductResponse {
//        return apiRequest {
//            productAPI.getAllProducts()
//        }
//    }

    suspend fun displayAllProducts() : MutableList<Product>?{
        try {
            val response = apiRequest{productAPI.getAllProducts()}
            saveInRoom(response.data!!)
            return productDao.getAllProducts()
        }
        catch(ex:Exception){
            Log.d("repo",ex.toString())
        }
        return productDao.getAllProducts()
    }

    private suspend fun saveInRoom(products: MutableList<Product>){
        for (product in products){
            productDao.insertProduct(product)
        }
    }
}