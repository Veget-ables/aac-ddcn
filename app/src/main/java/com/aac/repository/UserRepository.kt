package com.aac.repository

import android.arch.lifecycle.LiveData
import com.aac.api.RandomUserService
import com.aac.data.User
import com.aac.util.toLiveData
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserRepository @Inject constructor(private val service: RandomUserService) {

    fun suggestUsers(max: Int): LiveData<List<User>> = service.generate(max)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorResumeNext(Flowable.empty())
            .map { it.results }
            .toLiveData()
}