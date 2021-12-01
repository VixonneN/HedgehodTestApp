package com.example.hedgehodtestapp.data.mapper

import com.example.hedgehodtestapp.data.data_source.Root
import com.example.hedgehodtestapp.data.data_source.Value
import com.example.hedgehodtestapp.domain.entity.RootEntity
import com.example.hedgehodtestapp.domain.entity.ValueEntity

fun Root.toRootEntity(root: Root): RootEntity {
    return RootEntity(
        type = root.type,
        value = root.value
    )
}

fun Value.toValueEntity(value: Value): ValueEntity =
    ValueEntity(
        id = value.id,
        joke = value.joke,
        categories = value.categories
    )
