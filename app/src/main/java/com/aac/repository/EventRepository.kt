package com.aac.repository

import com.aac.data.Event
import com.aac.db.EventDao
import com.aac.db.SampleDb
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventRepository @Inject constructor(
        private val db: SampleDb,
        private val dao: EventDao) {

    fun insert(event: Event): Long {
        return dao.insert(event)
    }

    fun update(event: Event) {
        dao.update(event)
    }

    fun delete(event: Event) {
        dao.delete(event)
    }

    fun findAll() {
//        dao.findAll()
    }
}