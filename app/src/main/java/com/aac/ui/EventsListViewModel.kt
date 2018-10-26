package com.aac.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.aac.data.Event
import com.aac.repository.EventRepository
import javax.inject.Inject

class EventsListViewModel @Inject constructor(repo: EventRepository) : ViewModel() {
    private var _events = MutableLiveData<List<Event>>()
    var events: LiveData<List<Event>> = _events
}