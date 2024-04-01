package com.mert.mvvmandroidarchitecture.data.repository

import com.mert.mvvmandroidarchitecture.data.model.Shoe
import com.google.gson.annotations.SerializedName

data class ApiResponse (
    @SerializedName("message")
    val message: String? = null,

    @SerializedName("data")
    val data: List<Shoe>? = null,

    @SerializedName("isSuccessful")
    val isSuccessful: Boolean? = null
)