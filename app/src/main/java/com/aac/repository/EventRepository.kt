package com.aac.repository

import com.aac.data.Event
import com.aac.db.EventDao
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventRepository @Inject constructor(
        private val dao: EventDao) {

    fun insertEvent(scope: CoroutineScope, event: Event) {
        scope.launch {
            dao.insert(event)
        }
    }

    fun update(event: Event) {
        dao.update(event)
    }

    fun delete(event: Event) {
        dao.delete(event)
    }

    fun findAll() = dao.findAll()
}