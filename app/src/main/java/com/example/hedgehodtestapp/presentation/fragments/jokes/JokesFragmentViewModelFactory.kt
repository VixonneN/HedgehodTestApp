package com.example.hedgehodtestapp.presentation.fragments.jokes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hedgehodtestapp.data.repository.MainReposImpl
import com.example.hedgehodtestapp.domain.use_cases.GetJokesUseCase

class JokesFragmentViewModelFactory : ViewModelProvider.Factory {

    private val repos = MainReposImpl()

    private val getJokesUseCase = GetJokesUseCase(repos)

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return JokesFragmentViewModel(getJokesUseCase) as T
    }
}