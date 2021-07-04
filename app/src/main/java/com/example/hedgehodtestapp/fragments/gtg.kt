package com.example.hedgehodtestapp.fragments

import com.example.hedgehodtestapp.network.NetworkModule
import com.example.hedgehodtestapp.network.Root
import com.example.hedgehodtestapp.recycleView.JokesModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class gtg {
    private fun ggg() {
        val networkModule = NetworkModule()
        val number = 3
        networkModule.api().getSomeJokes(number).enqueue(object : Callback<Root?> {
            override fun onResponse(call: Call<Root?>, response: Response<Root?>) {
                val body = response.body()
                body!!.value[0].joke
            }

            override fun onFailure(call: Call<Root?>, t: Throwable) {}
        })
    }
}