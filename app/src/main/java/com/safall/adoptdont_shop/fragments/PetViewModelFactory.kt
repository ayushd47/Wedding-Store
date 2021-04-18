package com.safall.adoptdont_shop.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.safall.adoptdont_shop.repository.PetRepository


class PetViewModelFactory(val repository: PetRepository): ViewModelProvider.Factory {
    override fun <T: ViewModel?> create(modelClass:Class<T>):T{
        return PetViewModel(repository) as T
    }
}