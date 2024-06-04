package com.lukaslechner.coroutineusecasesonandroid.playground.structuredconcurrency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

val scope = CoroutineScope(Dispatchers.Default)
fun main() = runBlocking {
    val job = scope.launch{
        delay(100)
        println("coroutine completed")
    }
    job.invokeOnCompletion { throwable ->
        println("coroutine was cancelled $throwable")
    }
    delay(50)
    onDestroy()
}

fun onDestroy() {
    println("Life time of scope ends ")
    scope.cancel()
}