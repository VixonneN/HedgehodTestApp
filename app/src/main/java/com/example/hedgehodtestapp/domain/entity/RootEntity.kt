package com.example.hedgehodtestapp.domain.entity


data class RootEntity(
    val type: String,
    val value: List<ValueEntity>
)