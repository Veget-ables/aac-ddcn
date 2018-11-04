package com.aac.data

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity

@Entity(
        primaryKeys = ["id"]
)
data class Event(
        var id: Int,
        val title: String,
        @field:Embedded(prefix = "user_")
        val user: User)