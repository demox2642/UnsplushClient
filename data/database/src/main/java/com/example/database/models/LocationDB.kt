package com.example.database.models

import androidx.room.Embedded

data class LocationDB(
    var city: String? = null,
    var country: String? = null,
    @Embedded
    var position: PositionDB? = null
)
