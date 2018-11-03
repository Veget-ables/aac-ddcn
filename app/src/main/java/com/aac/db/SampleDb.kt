package com.aac.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.aac.data.Event
import com.aac.data.User
import javax.inject.Singleton

@Singleton
@Database(entities = [Event::class, User::class], version = 1)
abstract class SampleDb : RoomDatabase() {
    abstract fun eventDao(): EventDao

    abstract fun userDao(): UserDao
}