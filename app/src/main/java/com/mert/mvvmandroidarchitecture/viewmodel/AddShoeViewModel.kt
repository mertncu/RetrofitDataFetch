package com.mert.mvvmandroidarchitecture.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mert.mvvmandroidarchitecture.data.model.Shoe
import com.mert.mvvmandroidarchitecture.data.repository.ApiService
import kotlinx.coroutines.launch

class AddShoeViewModel (private val apiService: ApiService) : ViewModel() {

    fun addShoe(shoe: Shoe) {
        viewModelScope.launch {
            apiService.addShoes(shoe)
        }
    }
}