package com.aac.ui.event

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.aac.api.SuggestionUser
import com.aac.data.User
import com.aac.repository.UserRepository
import com.aac.ui.ScopedViewModel
import javax.inject.Inject

class EventViewModel @Inject constructor(private val userRepo: UserRepository)
    : ScopedViewModel() {

    val eventId = MutableLiveData<Long>()

    val suggestionUsers: LiveData<List<SuggestionUser>> = Transformations.switchMap(eventId) {
        userRepo.suggestUsers(5)
    }

    val joinedUsers: LiveData<List<User>> = Transformations.switchMap(eventId) {
        userRepo.findUsers(it)
    }
}
