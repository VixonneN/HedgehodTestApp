package com.example.hedgehodtestapp.data.mapper

import com.example.hedgehodtestapp.data.data_source.Root
import com.example.hedgehodtestapp.data.data_source.Value
import com.example.hedgehodtestapp.domain.entity.RootEntity
import com.example.hedgehodtestapp.domain.entity.ValueEntity

fun Root.toRootEntity(): RootEntity =
    RootEntity(type, value.map {
        it.toValueEntity()
    })

fun Value.toValueEntity(): ValueEntity =
    ValueEntity(id, joke, categories)
