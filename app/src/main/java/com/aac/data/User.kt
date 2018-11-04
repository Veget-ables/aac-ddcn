package com.aac.data

import android.arch.persistence.room.Entity

@Entity(
        primaryKeys = ["email"]
)
data class User(
        var eventId: Int,
        var gender: String,
        val firstName: String,
        val lastName: String,
        val email: String)

