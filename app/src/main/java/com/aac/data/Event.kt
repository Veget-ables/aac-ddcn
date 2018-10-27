package com.aac.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Event(val name: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}