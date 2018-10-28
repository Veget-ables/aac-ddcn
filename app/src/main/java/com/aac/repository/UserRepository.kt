package com.aac.repository

import android.arch.lifecycle.LiveData
import com.aac.api.RandomUserService
import com.aac.data.Result
import com.aac.data.User
import com.aac.db.UserDao
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val service: RandomUserService,
                                         private val userDao: UserDao) {

    suspend fun loadUser(max: Int): List<User> {
        val users: LiveData<List<User>> = userDao.find(max)

        val result: Response<Result> = users.value?.let {
            val lack = max - it.size
            if (lack <= 0) return it
            service.generate(lack).await()
        } ?: service.generate(max).await()

        if (result.isSuccessful) {
            return result.body()?.results ?: throw RuntimeException()
        } else {
            throw java.lang.RuntimeException()
        }
    }
}