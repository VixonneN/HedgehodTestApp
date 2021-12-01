package com.example.hedgehodtestapp.domain.use_cases

import com.example.hedgehodtestapp.data.network.Api
import com.example.hedgehodtestapp.domain.repository.MainRepos

class GetJokesUseCase(private val repository: MainRepos) {

    suspend operator fun invoke() : Api =
        repository.getJokes()

}