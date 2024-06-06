package com.lukaslechner.coroutineusecasesonandroid.playground.cancellation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield

fun main() = runBlocking<Unit> {
    val job = launch(Dispatchers.Default) {
        repeat(10) { index ->
//            yield()
//            ensureActive()
            if (isActive.not()) return@launch
            withContext(NonCancellable){}
            println("operation number $index")
            Thread.sleep(100)
        }
    }
    job.invokeOnCompletion {
        println("job.invokeOnCompletion $it")
    }
    delay(250)
    println("Cancelling Coroutine ")
    job.cancel()
}