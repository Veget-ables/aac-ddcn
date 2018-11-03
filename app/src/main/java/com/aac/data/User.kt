package com.aac.data

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity

@Entity(
        primaryKeys = ["email"]
)
data class User(
        var gender: String,
        @field:Embedded(prefix = "name_")
        val name: Name,
        val email: String ) {

    data class Name(val title: String, val first: String, val last: String)
}

