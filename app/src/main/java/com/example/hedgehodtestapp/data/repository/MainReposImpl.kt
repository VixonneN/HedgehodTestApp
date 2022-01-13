package com.example.hedgehodtestapp.data.repository

import com.example.hedgehodtestapp.data.mapper.toRootEntity
import com.example.hedgehodtestapp.data.network.NetworkModule
import com.example.hedgehodtestapp.domain.entity.RootEntity
import com.example.hedgehodtestapp.domain.repository.MainRepos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainReposImpl : MainRepos {

    private val networkModule = NetworkModule()

    override suspend fun getJokes(number: Int): RootEntity {
        return withContext(Dispatchers.IO) {
            networkModule.api().getSomeJokes(number).toRootEntity()
        }
    }
}