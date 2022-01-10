package com.example.hedgehodtestapp.data.network

import com.example.hedgehodtestapp.data.data_source.Root
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("random/{number}")
    suspend fun getSomeJokes(@Path("number") number: Int) : Root
}