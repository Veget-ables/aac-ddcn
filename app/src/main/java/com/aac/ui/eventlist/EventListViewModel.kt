package com.aac.ui.eventlist

import android.arch.lifecycle.ViewModel
import com.aac.data.Event
import com.aac.data.User
import com.aac.repository.EventRepository
import kotlinx.coroutines.experimental.CoroutineScope
import javax.inject.Inject

class EventListViewModel @Inject constructor(private val eventRepo: EventRepository)
    : ViewModel() {

    val events = eventRepo.findAll()

    fun addEvent(scope: CoroutineScope) {
        val event = Event(
                title = "No:" + (Math.random() * 1000).toInt().toString(),
                user = User(
                        gender = "male",
                        name = User.Name("hoge", "hoge", "hoge"),
                        email = "hoge@example.com")
        )
        eventRepo.insertEvent(scope, event)
    }

}

