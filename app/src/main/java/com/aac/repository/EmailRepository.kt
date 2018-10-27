package com.aac.repository

import com.aac.api.RandomUserService
import javax.inject.Inject

class EmailRepository @Inject constructor(private val service: RandomUserService) {

    fun generate(num: Int) = service.generate(num)
}