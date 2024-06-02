package com.example.paginationcatapi.models.api

import com.example.paginationcatapi.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiHelper {
    val api by lazy{
        Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(Api::class.java)
    }
}