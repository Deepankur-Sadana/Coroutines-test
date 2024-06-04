package com.lukaslechner.coroutineusecasesonandroid.playground.structuredconcurrency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable.invokeOnCompletion
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() = runBlocking<Unit> {
    val scope = CoroutineScope(Dispatchers.Default)

    scope.coroutineContext[Job]!!.invokeOnCompletion { throwable ->
        println("Parent 1 completed")

    }
    val childCoroutine1Job = scope.launch {
        delay(1000)
        println("Coroutine 1 completed")
    }
    childCoroutine1Job.invokeOnCompletion { throwable ->
        println("coroutine 1 $throwable")
    }

    val childCoroutine2Job = scope.launch {
        delay(1000)
        println("Coroutine 2 completed")
    }
    childCoroutine2Job.invokeOnCompletion { throwable ->
        println("coroutine 2 $throwable")
    }
    delay(200)
    scope.cancel()
//    scope.cancel()
//    scope.coroutineContext[Job]!!.cancelAndJoin()

}
