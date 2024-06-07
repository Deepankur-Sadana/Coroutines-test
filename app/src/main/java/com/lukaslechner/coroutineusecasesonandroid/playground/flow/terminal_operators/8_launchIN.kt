package com.lukaslechner.coroutineusecasesonandroid.playground.flow.terminal_operators

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

fun main() {
    val flow = flow {
        kotlinx.coroutines.delay(100)
        emit(1)
        kotlinx.coroutines.delay(100)
        emit(2)
    }

    val scope = CoroutineScope(EmptyCoroutineContext)
//    val launchIn = flow.launchIn(scope)
//    launchIn.invokeOnCompletion {
//    }

    flow
        .onEach { println("launchIn item $it") }
//        .launchIn(scope)

    scope.launch {
        flow.collect{
            println("A collecting $it")
        }
        println("A over...")
        flow.collect{
            println("B collecting $it")
        }
    }

    scope.launch {

    }

    Thread.sleep(5000)
}