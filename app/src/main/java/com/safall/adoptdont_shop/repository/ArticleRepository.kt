package com.safall.adoptdont_shop.repository

import android.util.Log
import com.safall.adoptdont_shop.api.ArticleAPI
import com.safall.adoptdont_shop.api.MyApiRequest
import com.safall.adoptdont_shop.api.ServiceBuilder
import com.safall.adoptdont_shop.dao.ArticleDAO
import com.safall.adoptdont_shop.dao.ProductDAO
import com.safall.adoptdont_shop.entity.Article
import com.safall.adoptdont_shop.entity.Product
import com.safall.adoptdont_shop.response.ArticleResponse


class ArticleRepository  (private val articleDao: ArticleDAO): MyApiRequest() {
    private val articleAPI = ServiceBuilder.buildService(ArticleAPI::class.java)
//    suspend fun getAllArticles(): ArticleResponse {
//        return apiRequest {
//            articleAPI.getAllArticles()
//        }
//    }

    suspend fun displayAllArticles() : MutableList<Article>?{
        try {
            val response = apiRequest{articleAPI.getAllArticles()}
            saveInRoom(response.data!!)
            return articleDao.getAllArticles()
        }
        catch(ex:Exception){
            Log.d("repo",ex.toString())
        }
        return articleDao.getAllArticles()
    }

    private suspend fun saveInRoom(articles: MutableList<Article>){
        for (article in articles){
            articleDao.insertArticle(article)
        }
    }
}