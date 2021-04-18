package com.safall.adoptdont_shop.response

import com.safall.adoptdont_shop.entity.Article

data class ArticleResponse(
        val success : Boolean? = null,
        val data: MutableList<Article>? = null
)
