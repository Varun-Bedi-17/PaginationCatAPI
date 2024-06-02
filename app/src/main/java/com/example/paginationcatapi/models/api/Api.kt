package com.example.paginationcatapi.models.api

import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("v1/images/search")
    suspend fun getDataFromApi(@Query("limit")  limit : Int, @Query("page") page : Int, @Query("order") order : String) : ApiDataModel
}