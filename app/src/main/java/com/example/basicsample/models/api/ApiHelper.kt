package com.example.basicsample.models.api

import com.example.basicsample.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiHelper {
    val api by lazy{
        Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(Api::class.java)
    }
}