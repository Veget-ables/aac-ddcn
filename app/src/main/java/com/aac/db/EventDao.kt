package com.aac.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.aac.data.Event

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(event: Event): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvents(event: List<Event>)


    @Update
    fun update(event: Event)

    @Delete
    fun delete(event: Event)

    @Query("SELECT * FROM Event")
    fun findAll() : LiveData<List<Event>>
}
