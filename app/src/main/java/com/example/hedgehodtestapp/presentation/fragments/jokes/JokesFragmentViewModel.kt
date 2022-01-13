package com.example.hedgehodtestapp.presentation.fragments.jokes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hedgehodtestapp.domain.entity.RootEntity
import com.example.hedgehodtestapp.domain.use_cases.GetJokesUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class JokesFragmentViewModel(
    private val getJokesUseCase: GetJokesUseCase
) : ViewModel() {

    private val handler = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is UnknownHostException -> _exception.value = "Не найден хост"
            is SocketTimeoutException -> _exception.value = "Время ожидания вышло"
            is HttpException -> {
                when (throwable.code()) {
                    403 -> _exception.value = "Доступ запрещён"
                    404 -> _exception.value = "Ничего не найдено"
                }
            }
        }
    }

    private val _jokes = MutableLiveData<RootEntity>()
    val jokes: LiveData<RootEntity> = _jokes

    private val _exception = MutableLiveData<String>()
    val exception: LiveData<String> = _exception

    fun jokesRequest(number: String) {
        if (number == "" || number.isEmpty()) {
            _exception.value = "Введите верное значение"
        } else {
            viewModelScope.launch(handler) {
                val response = getJokesUseCase.invoke(number.toInt())
                _jokes.value = response
            }
        }
    }
}