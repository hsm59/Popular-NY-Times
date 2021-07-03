package com.hussainm.popularnytimes.viewmodel

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hussainm.popularnytimes.event.Event
import javax.inject.Inject

/**
 * Handles navigation, dispatches new destination events to the nav controller
 */
class NavigationViewModel @Inject constructor(): ViewModel() {

    private val _destinationId = MutableLiveData<Event<Pair<Int, Bundle?>>>()

    val destinationId: LiveData<Event<Pair<Int, Bundle?>>> = _destinationId

    fun setNewDestination(destinationId: Pair<Int, Bundle?>) {
        _destinationId.value = Event(destinationId)
    }

}