package com.example.hedgehodtestapp.recycleView

data class JokesModel(private var jokes : String?) {

    fun getJokes() : String? {
        return jokes
    }

    fun setJokes(value: String){
        jokes = value
    }

}