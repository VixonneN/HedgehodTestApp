package com.example.hedgehodtestapp.domain.use_cases

import com.example.hedgehodtestapp.domain.repository.MainRepos

class GetJokesUseCase(private val repository: MainRepos) {

    operator fun invoke(number: Int){
        repository.getJokes(number)
    }
}