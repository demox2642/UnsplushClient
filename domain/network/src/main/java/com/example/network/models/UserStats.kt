package com.example.network.models

data class UserStats(
    var username: String? = null,
    var downloads: UserStat? = null,
    var views: UserStat? = null,
    var likes: UserStat? = null
)