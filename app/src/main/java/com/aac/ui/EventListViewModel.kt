package com.aac.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.aac.data.User
import com.aac.repository.UserRepository
import com.aac.repository.EventRepository
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.android.Main
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class EventListViewModel @Inject constructor(
        private val eventRepo: EventRepository,
        private val userRepo: UserRepository) : ViewModel() {
    private var _users = MutableLiveData<List<User>>()
    var users: LiveData<List<User>> = _users

    fun generateEmails(num: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            val result = userRepo.generate(num).await()
            _users.postValue(result.body()?.results)
        }
    }
}