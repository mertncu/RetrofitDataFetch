package com.mert.mvvmandroidarchitecture.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mert.mvvmandroidarchitecture.data.model.Shoe
import com.mert.mvvmandroidarchitecture.data.repository.ApiService
import kotlinx.coroutines.launch

class MainViewModel(private val apiService: ApiService) : ViewModel() {

    fun addShoe(shoe: Shoe) {
        viewModelScope.launch {
            apiService.addShoes(shoe)
        }
    }

    fun deleteShoe(id: Int) {
        viewModelScope.launch {
            apiService.deleteShoe(id)
        }
    }

    fun getShoes() {
        viewModelScope.launch {
            apiService.getShoes()
        }
    }


}