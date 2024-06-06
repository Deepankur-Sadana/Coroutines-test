package com.lukaslechner.coroutineusecasesonandroid.playground.exceptionhandling

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    val exceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
            println("exceptionHAndler caught $throwable")
        }
    val scope = CoroutineScope(Job() + exceptionHandler)

    scope.async {
        async {
            delay(200)
            throw RuntimeException()
        }
    }

    Thread.sleep(500)
}