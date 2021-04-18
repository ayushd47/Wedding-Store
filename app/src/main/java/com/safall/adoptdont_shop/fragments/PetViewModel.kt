package com.safall.adoptdont_shop.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.safall.adoptdont_shop.entity.Pet
import com.safall.adoptdont_shop.entity.Product
import com.safall.adoptdont_shop.repository.PetRepository
import com.safall.adoptdont_shop.repository.ProductRepository
import kotlinx.coroutines.launch

class PetViewModel(val repository: PetRepository): ViewModel() {
    private val _text = MutableLiveData<MutableList<Pet>>()

    val text: LiveData<MutableList<Pet>>
        get()=_text

    fun getAllPets(){
        viewModelScope.launch{
            val text = repository.displayAllPets()
            _text.value = text!!
        }
    }
}