package com.example.hedgehodtestapp.domain.entity

data class ValueEntity(
    var id: Int,
    var joke: String,
    var categories: List<String>
)