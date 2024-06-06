package com.lukaslechner.coroutineusecasesonandroid.playground.exceptionhandling

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

fun main() {
    val exceptionHandler =
        CoroutineExceptionHandler { coroutineContext: CoroutineContext,
                                    throwable: Throwable ->
            println("Caught $throwable in CoroutineExceptionalHandler")
        }
    val scope = CoroutineScope(Job())
    scope.launch() {
        launch(exceptionHandler) {
            throw RuntimeException("asdsa")
        }
    }
    Thread.sleep(100)
}
