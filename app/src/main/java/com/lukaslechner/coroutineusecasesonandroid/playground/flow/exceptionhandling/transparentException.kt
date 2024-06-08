package com.lukaslechner.coroutineusecasesonandroid.playground.flow.exceptionhandling

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import java.lang.RuntimeException

suspend fun main(): Unit = coroutineScope {

    flow {
        try {
            emit(1)
        } catch (e: Exception) {
            println("caught in try/catch")
        }
    }.collect { emitted ->
        throw Exception("Exception in collect")
    }
}


