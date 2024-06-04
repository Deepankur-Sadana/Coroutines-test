package com.lukaslechner.coroutineusecasesonandroid.playground.structuredconcurrency

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.lang.RuntimeException
import kotlin.coroutines.CoroutineContext

fun main() {

    val exceptionHAndler = CoroutineExceptionHandler{
        coroutineContext: CoroutineContext, throwable: Throwable ->
        println("caught exception $coroutineContext , $throwable")
    }

    val scope = CoroutineScope(SupervisorJob() + exceptionHAndler)
    scope.launch {
        println("Coroutines 1 start")
        delay(50)
        println("Coroutines 1 will fail")
        throw RuntimeException("")
    }
    scope.launch {
        println("Coroutines 2 start")
        delay(500)
        println("Coroutines 2 finished")
    }.invokeOnCompletion { throwable ->
        println("coroutine 2 $throwable")
    }

    Thread.sleep(1000)
    print("scope is cancelled ${scope.isActive.not()}")

}