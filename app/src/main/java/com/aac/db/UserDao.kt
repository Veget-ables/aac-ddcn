package com.aac.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.aac.data.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(user: List<User>)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM user LIMIT :num")
    fun findUsers(num: Int): LiveData<List<User>>

    @Query("SELECT * FROM user")
    fun findAll(): LiveData<List<User>>

    @Query("SELECT * FROM user WHERE eventId =:id")
    fun findEventUsers(id: Int): LiveData<List<User>>
}