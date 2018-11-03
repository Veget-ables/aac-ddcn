package com.aac.repository

import com.aac.api.RandomUserService
import com.aac.data.Result
import com.aac.db.UserDao
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.launch
import java.net.UnknownHostException
import javax.inject.Inject

class UserRepository @Inject constructor(private val service: RandomUserService,
                                         private val userDao: UserDao) {

    fun findAll() = userDao.findAll()

    fun refreshUsers(max: Int, scope: CoroutineScope) {
        scope.launch {
            try {
                val response: Result = service.generate(max).await()
                userDao.insertUsers(response.results)

            } catch (e: Throwable) {
                when (e) {
                    is UnknownHostException -> throw UnknownHostException()
                    is IllegalArgumentException -> throw IllegalArgumentException()
                    else -> throw RuntimeException()
                }
            }
        }
    }

}