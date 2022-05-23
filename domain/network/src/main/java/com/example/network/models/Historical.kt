package com.example.network.models

data class Historical(
    var change: Int? = null,
    var average: Int? = null,
    var resolution: String? = null,
    var quantity: Int? = null,
    var values: List<Value>? = null
)
