package com.aac.ui.event

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.aac.data.User
import com.aac.repository.UserRepository
import javax.inject.Inject

class EventViewModel @Inject constructor(private val userRepo: UserRepository)
    : ViewModel() {

    val eventId = MutableLiveData<Int>()

    val users: LiveData<List<User>> = Transformations.switchMap(eventId) {
        userRepo.suggestUsers(5)
    }

}