package com.aac.data

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Event(val title: String,
                 @field:Embedded(prefix = "user_")
                 val user: User) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}