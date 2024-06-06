package com.lukaslechner.coroutineusecasesonandroid.playground.exceptionhandling

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {

    val exceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Caught exception: $throwable")
    }
    val scope = CoroutineScope(Job())
    scope.launch(exceptionHandler) {
        launch {
            println("starting coroutine 1")
            delay(100)
            throw RuntimeException()
        }

        launch {
            println("starting coroutine 2")
            delay(3000)
            println("coroutine 2 completed")
        }
    }
    Thread.sleep(5000)
}