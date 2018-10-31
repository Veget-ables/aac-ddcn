package com.aac.repository

import com.aac.api.RandomUserService
import com.aac.db.UserDao
import javax.inject.Inject

class UserRepository @Inject constructor(private val service: RandomUserService,
                                         private val userDao: UserDao) {

    fun loadUser(max: Int) = service.generate(max)
}