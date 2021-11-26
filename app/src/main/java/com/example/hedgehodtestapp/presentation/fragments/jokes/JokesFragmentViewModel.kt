package com.example.hedgehodtestapp.presentation.fragments.jokes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hedgehodtestapp.data.data_source.Root
import com.example.hedgehodtestapp.data.network.NetworkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JokesFragmentViewModel : ViewModel() {

    private val _jokes = MutableLiveData<Root>()
    val jokes : LiveData<Root> = _jokes

    fun networkModule(number: Int) {
        val networkModule = NetworkModule()
        networkModule.api().getSomeJokes(number).enqueue(object : Callback<Root> {
            override fun onResponse(call: Call<Root>, response: Response<Root>) {
                _jokes.value = response.body()
            }

            override fun onFailure(call: Call<Root>, t: Throwable) {

            }
        })
    }
}