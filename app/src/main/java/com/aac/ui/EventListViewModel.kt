package com.aac.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.aac.data.Email
import com.aac.repository.EmailRepository
import com.aac.repository.EventRepository
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class EventListViewModel @Inject constructor(
        private val eventRepo: EventRepository,
        private val emailRepo: EmailRepository) : ViewModel() {
    private var _emails = MutableLiveData<List<Email>>()
    var emails: LiveData<List<Email>> = _emails

    fun generateEmails(num: Int) {
        GlobalScope.launch {
            val result = emailRepo.generate(num).await()
            result.body()
            _emails.postValue(result.body()?.results)
        }
    }
}