package com.mert.mvvmandroidarchitecture.data.repository

import com.mert.mvvmandroidarchitecture.data.model.Shoe
import retrofit2.Response
import retrofit2.http.Body

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.DELETE

interface ApiService {
    @GET("/api/shoe")
    suspend fun getShoes(): List<Shoe>

    @POST("/api/shoe")
    suspend fun addShoes(@Body shoe: Shoe): ApiResponse

    @PUT("/api/shoe/{id}")
    suspend fun updateShoe(@Path("id") id: Int, @Body shoe: Shoe): Response<ApiResponse>

    @DELETE("/api/shoe/{id}")
    suspend fun deleteShoe(@Path("id") id: Int): Response<ApiResponse>

}