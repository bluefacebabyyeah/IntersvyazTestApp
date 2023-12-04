package com.example.intersvyaztestapp.services

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventBus @Inject constructor() {
    val events get() = _events.asSharedFlow()
    private val _events = MutableSharedFlow<Event>(1)

    suspend fun emit(event: Event) =
        _events.emit(event)

    sealed interface Event {
        data class OpenDetailsPage(val id: Int): Event
    }
}