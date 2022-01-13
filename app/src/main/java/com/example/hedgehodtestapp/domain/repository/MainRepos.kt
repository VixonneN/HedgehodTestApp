package com.example.hedgehodtestapp.domain.repository

import com.example.hedgehodtestapp.domain.entity.RootEntity

interface MainRepos {

    suspend fun getJokes(number: Int) : RootEntity
}