package com.example.hedgehodtestapp.network

import com.example.hedgehodtestapp.data.Root
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("random/{number}")
    fun getSomeJokes(@Path("number") number: Int) : Call<Root>
}