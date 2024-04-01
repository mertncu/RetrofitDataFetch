package com.mert.mvvmandroidarchitecture.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mert.mvvmandroidarchitecture.data.model.Shoe
import com.mert.mvvmandroidarchitecture.data.repository.ApiService
import kotlinx.coroutines.launch

class EditShoeViewModel(private val apiService: ApiService) : ViewModel() {

    fun updateShoe(shoe: Shoe) {
        viewModelScope.launch {
            apiService.updateShoe(shoe.id, shoe)
        }
    }
}
