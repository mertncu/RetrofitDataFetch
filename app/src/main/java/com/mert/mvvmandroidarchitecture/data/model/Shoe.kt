package com.mert.mvvmandroidarchitecture.data.model

import com.google.gson.annotations.SerializedName

data class Shoe(
    @SerializedName("ShoeBrand")
    val brand: String,

    @SerializedName("ShoeColour")
    val color: String,

    @SerializedName("ShoeId")
    val id: Int,

    @SerializedName("ShoeModel")
    val model: String,

    @SerializedName("ShoePrice")
    val price: Int?,

    @SerializedName("ShoeSize")
    val size: Int
)