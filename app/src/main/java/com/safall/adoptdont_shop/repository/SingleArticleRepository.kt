package com.safall.adoptdont_shop.repository

import com.safall.adoptdont_shop.api.MyApiRequest
import com.safall.adoptdont_shop.api.ServiceBuilder
import com.safall.adoptdont_shop.api.SingleArticleAPI

import com.safall.adoptdont_shop.response.ArticleResponse


class SingleArticleRepository: MyApiRequest() {
    private val singleArticleAPI = ServiceBuilder.buildService(SingleArticleAPI::class.java)

    //Display Single Article
    suspend fun getSingleArticle(id:String): ArticleResponse {
        return apiRequest {
            singleArticleAPI.showSingleArticle(id)
        }
    }
}