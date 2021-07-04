package com.example.hedgehodtestapp.network

import com.google.gson.annotations.SerializedName

data class Value(
    var id: Int,
    var joke: String,
    var categories: List<String>)