package com.example.network.models

enum class Order(val order: String) {
    LATEST("latest"),
    OLDEST("oldest"),
    POPULAR("popular")
}
