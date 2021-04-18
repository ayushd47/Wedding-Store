package com.safall.adoptdont_shop.api

import com.safall.adoptdont_shop.response.ArticleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SingleArticleAPI {
    //Single article show
    @GET("/article/singleshow/{id}")
    suspend fun showSingleArticle(
            @Path("id")id:String
    ): Response<ArticleResponse>
}