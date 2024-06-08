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

    launch {
        val stocksFlow = stocksFlow()




        stocksFlow
            .onCompletion { cause ->
                if (cause == null) {
                    println("flow completed successfully")
                } else {
                    println("flow completed exceptionally $cause")
                }
            }
            .catch {
                cause: Throwable -> println(".catch $cause")
            }
            .collect { stock ->
                throw Exception("crash in collect")
                println("Collected $stock")
            }.runCatching {  }
    }
}

private suspend fun stocksFlow(): Flow<String> = flow {
    emit("Apple")
    emit("Microsoft")

    throw RuntimeException("fatal.....")
}

private suspend fun fallbackFlow(): Flow<String> = flow {
    emit("Honda")
    emit("Maruti")

}