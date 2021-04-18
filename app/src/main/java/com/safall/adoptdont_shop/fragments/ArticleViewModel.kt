package com.safall.adoptdont_shop.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.safall.adoptdont_shop.entity.Article
import com.safall.adoptdont_shop.repository.ArticleRepository

import kotlinx.coroutines.launch

class ArticleViewModel (val repository: ArticleRepository): ViewModel(){
    private val _text = MutableLiveData<MutableList<Article>>()

    val text: LiveData<MutableList<Article>>
        get()=_text

    fun getAllArticles(){
        viewModelScope.launch{
            val text = repository.displayAllArticles()
            _text.value = text!!
        }
    }
}