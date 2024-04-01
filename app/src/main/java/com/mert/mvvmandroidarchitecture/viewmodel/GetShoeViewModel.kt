package com.mert.mvvmandroidarchitecture.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mert.mvvmandroidarchitecture.data.model.Shoe
import com.mert.mvvmandroidarchitecture.data.repository.ApiService
import kotlinx.coroutines.launch

class GetShoeViewModel(private val apiService: ApiService) : ViewModel() {

    private val _shoes = MutableLiveData<List<Shoe>>()
    val shoes: LiveData<List<Shoe>> = _shoes

    fun getShoes() {
        viewModelScope.launch {
            _shoes.value = apiService.getShoes()
        }
    }
}