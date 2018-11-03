package com.aac.ui

import android.arch.lifecycle.ViewModel
import com.aac.data.Event
import com.aac.repository.EventRepository
import com.aac.repository.UserRepository
import kotlinx.coroutines.experimental.CoroutineScope
import javax.inject.Inject

class EventListViewModel @Inject constructor(
        private val eventRepo: EventRepository,
        private val userRepo: UserRepository) : ViewModel() {

    val users = userRepo.findAll()

    val events = eventRepo.findAll()

    fun insertEvents(events: List<Event>, scope: CoroutineScope) {
        eventRepo.insertEvents(events, scope)
    }

    fun refreshUsers(scope: CoroutineScope) {
        userRepo.refreshUsers(10, scope)
    }
}

