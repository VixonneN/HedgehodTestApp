package com.example.hedgehodtestapp.presentation.fragments.jokes

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hedgehodtestapp.data.data_source.Root
import com.example.hedgehodtestapp.data.network.NetworkModule
import com.example.hedgehodtestapp.domain.use_cases.GetJokesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class JokesFragmentViewModel(
    private val getJokesUseCase: GetJokesUseCase
    ) : ViewModel() {

    private val _jokes = MutableLiveData<Root>()
    val jokes : LiveData<Root> = _jokes

    fun jokesRequest(number: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = getJokesUseCase.invoke().getSomeJokes(number).execute()
                viewModelScope.launch(Dispatchers.Main) {
                    _jokes.value = response.body()
                }
            } catch (e: IOException) {
                Log.d(TAG, "jokesRequest: $e")
            } catch (e: HttpException) {
                Log.d(TAG, "jokesRequest: $e")
            }
        }
    }
}