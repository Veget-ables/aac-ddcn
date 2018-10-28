package com.aac.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.aac.data.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User): Long

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM user LIMIT :num")
    fun find(num : Int) : LiveData<List<User>>
}