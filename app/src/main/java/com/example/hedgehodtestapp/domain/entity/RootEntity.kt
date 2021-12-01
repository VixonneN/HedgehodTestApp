package com.example.hedgehodtestapp.domain.entity

import com.example.hedgehodtestapp.data.data_source.Value

data class RootEntity(
    val type: String,
    val value: List<Value>
)