package com.example.hedgehodtestapp.data.repository

import com.example.hedgehodtestapp.data.network.Api
import com.example.hedgehodtestapp.data.network.NetworkModule
import com.example.hedgehodtestapp.domain.repository.MainRepos

class MainReposImpl : MainRepos {

    private val networkModule = NetworkModule()

    override suspend fun getJokes(): Api {
        return networkModule.api()
    }
}