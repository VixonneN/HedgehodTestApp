package com.example.hedgehodtestapp.domain.repository

import com.example.hedgehodtestapp.data.data_source.Root
import com.example.hedgehodtestapp.data.network.Api
import retrofit2.Call

interface MainRepos {

    suspend fun getJokes() : Api
}