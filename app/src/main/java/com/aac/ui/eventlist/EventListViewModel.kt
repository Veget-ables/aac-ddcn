package com.aac.ui.eventlist

import com.aac.data.Event
import com.aac.data.User
import com.aac.repository.EventRepository
import com.aac.ui.ScopedViewModel
import javax.inject.Inject

class EventListViewModel @Inject constructor(private val eventRepo: EventRepository)
    : ScopedViewModel() {

    val events = eventRepo.findAll()

    fun addEvent() {
        val id = (Math.random() * 1000).toInt()
        val event = Event(
                id = id,
                title = "No:$id",
                user = User(
                        eventId = id,
                        gender = "male",
                        firstName = "hoge",
                        lastName = "hoge",
                        email = "hoge@example.com")
        )
        eventRepo.insertEvent(this, event)
    }

}

