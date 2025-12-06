package com.kkh.multimodule.domain.model.error

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ErrorHelper @Inject constructor(){
    private val _errorEvent = Channel<Throwable>(DEFAULT_BUFFER_SIZE)
    val errorEvent = _errorEvent.receiveAsFlow()

    suspend fun sendError(error: Throwable) {
        _errorEvent.send(error)
    }
}
