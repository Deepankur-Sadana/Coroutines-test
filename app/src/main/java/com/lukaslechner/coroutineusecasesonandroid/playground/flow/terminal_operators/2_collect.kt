package com.lukaslechner.coroutineusecasesonandroid.playground.flow.terminal_operators

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    val flow = flow {
        delay(100)
        println("Emitting 1st value")
        emit(1)

        delay(100)
        println("Emitting 1st value")
        emit(2)

        while (true) {
            delay(5000)
            println()
        }
    }
    val scope = CoroutineScope((Job()))
    scope.launch {
        flow.collect{
            println("collecting $it")
        }
    }.invokeOnCompletion {
        println("Completion listener of scope $it")
    }
    Thread.sleep(400)

}