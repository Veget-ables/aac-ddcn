package com.aac.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.aac.data.Result
import com.aac.extention.toLiveData
import com.aac.repository.EventRepository
import com.aac.repository.UserRepository
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EventListViewModel @Inject constructor(
        private val eventRepo: EventRepository,
        private val userRepo: UserRepository) : ViewModel() {

    val userId = MutableLiveData<Int>()

    val users: LiveData<Result> = Transformations.switchMap(userId) {
        userRepo.loadUser(10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(Flowable.empty())
                .toLiveData()
    }
}
