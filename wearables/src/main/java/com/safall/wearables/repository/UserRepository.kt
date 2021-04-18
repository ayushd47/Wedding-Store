package com.safall.wearables.repository

import com.safall.adoptdont_shop.api.MyApiRequest
import com.safall.adoptdont_shop.api.ServiceBuilder
import com.safall.adoptdont_shop.api.UserAPI
import com.safall.adoptdont_shop.entity.User
import com.safall.adoptdont_shop.response.LoginResponse
import com.safall.wearables.api.MyApiRequest
import com.safall.wearables.api.ServiceBuilder
import com.safall.wearables.api.UserAPI
import com.safall.wearables.entity.User
import com.safall.wearables.response.LoginResponse

class UserRepository : MyApiRequest() {
    private val userAPI = ServiceBuilder.buildService(UserAPI::class.java)

    suspend fun registerUser(user: User): LoginResponse {
        return apiRequest {
            userAPI.registerUser(user)
        }
    }

    suspend fun checkUser(user_email: String, user_password: String):LoginResponse{
        return apiRequest {
            userAPI.checkUser(user_email, user_password)
        }
    }

    //Retrieve User
    suspend fun userDetails(token:String,userToken:String): LoginResponse {
        return apiRequest {
            userAPI.retrieveUser(ServiceBuilder.token!!,userToken)
        }
    }
}