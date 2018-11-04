package com.aac.ui.event

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.aac.api.SuggestionUser
import com.aac.data.User
import com.aac.repository.UserRepository
import com.aac.ui.ScopedViewModel
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class EventViewModel @Inject constructor(private val userRepo: UserRepository)
    : ScopedViewModel() {

    val eventId = MutableLiveData<Int>()

    private var updateCount = MutableLiveData<Int>()

    var suggestionUsers: LiveData<List<SuggestionUser>> = Transformations.switchMap(updateCount) {
        userRepo.suggestUsers(5)
    }

    val participants: LiveData<List<User>> = Transformations.switchMap(eventId) {
        userRepo.findUsers(it)
    }

    fun addUser2Event(user: SuggestionUser) = eventId.value?.let {
        launch {
            userRepo.addEvent2User(it, user)
        }
    }

    fun updateSuggestionUsers() {
        updateCount.value = updateCount.value?.plus(1)
    }
}
