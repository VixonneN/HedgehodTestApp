package com.example.hedgehodtestapp.domain.use_cases

import com.example.hedgehodtestapp.domain.entity.RootEntity
import com.example.hedgehodtestapp.domain.repository.MainRepos

class GetJokesUseCase(private val repository: MainRepos) {

    suspend operator fun invoke(number: Int) : RootEntity =
        repository.getJokes(number)
}