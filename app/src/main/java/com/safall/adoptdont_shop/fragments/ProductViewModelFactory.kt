package com.safall.adoptdont_shop.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.safall.adoptdont_shop.repository.ProductRepository

class ProductViewModelFactory(val repository: ProductRepository):ViewModelProvider.Factory{
    override fun <T:ViewModel?> create(modelClass:Class<T>):T{
        return ProductViewModel(repository) as T
    }
}