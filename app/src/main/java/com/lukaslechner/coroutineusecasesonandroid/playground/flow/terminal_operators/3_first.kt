package com.lukaslechner.coroutineusecasesonandroid.playground.flow.terminal_operators

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    val flow = flow<Int> {

        for (i in 0..10) {
            delay(10)
            emit(i)
            println("Emitting I $i")

        }


    }
    val scope = CoroutineScope((Job()))
    scope.launch {
        val item = flow.last()
        println("collecting $item")
    }.invokeOnCompletion {
        println("Completion listener of scope $it")
    }
    Thread.sleep(400)

}