package com.aac.repository

import android.arch.lifecycle.LiveData
import com.aac.api.RandomUserService
import com.aac.api.SuggestionUser
import com.aac.db.UserDao
import com.aac.util.toLiveData
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserRepository @Inject constructor(
        private val userDao: UserDao,
        private val service: RandomUserService) {

    fun suggestUsers(max: Int): LiveData<List<SuggestionUser>> = service.generate(max)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorResumeNext(Flowable.empty())
            .map { it.results }
            .toLiveData()

    fun findUsers(eventId: Long) = userDao.findEventUsers(eventId)

}