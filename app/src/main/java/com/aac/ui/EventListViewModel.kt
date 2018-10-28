package com.aac.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.aac.data.User
import com.aac.repository.EventRepository
import com.aac.repository.UserRepository
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.Main
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject
import kotlin.coroutines.experimental.CoroutineContext

class EventListViewModel @Inject constructor(
        private val eventRepo: EventRepository,
        private val userRepo: UserRepository) : ViewModel(), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job


    private var _users = MutableLiveData<List<User>>()

    var users: LiveData<List<User>> = _users

    fun reloadUsers(num: Int) {
        launch {
            val users = userRepo.loadUser(num)
            _users.postValue(users)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
