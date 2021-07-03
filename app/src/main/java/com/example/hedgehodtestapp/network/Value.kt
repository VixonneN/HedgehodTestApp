package com.example.hedgehodtestapp.network

import com.google.gson.annotations.SerializedName

data class Value(
    @SerializedName("id") var id: Int,
    @SerializedName("joke") var joke: String,
    var categories: List<String>)