package com.mert.mvvmandroidarchitecture.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mert.mvvmandroidarchitecture.data.repository.ApiService
import kotlinx.coroutines.launch

class DeleteShoeViewModel(private val apiService: ApiService) : ViewModel() {

    fun deleteShoe(id: Int) {
        viewModelScope.launch {
            apiService.deleteShoe(id)
        }
    }
}