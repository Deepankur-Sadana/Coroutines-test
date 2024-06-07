package com.lukaslechner.coroutineusecasesonandroid.playground.flow.builders

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.delayEach
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
//    val firstFlow = flowOf(1, 2, 3, 4, 5)
//        .onEach { delay(100) }
//        .collect { collectedItem ->
//            println("collectedItem item: --- $collectedItem")
//        }

    listOf("1", "2", "#").asFlow()
    println("DONE observing")

    val secondFlow = flowOf(1, 2, 3)
    secondFlow.collect {}

    flow {
        for (i in 1..5) {
            delay(2000)
            emit("item emitted after 2000 ms $i")

            emitAll(secondFlow)
        }
    }.collect {
        println("collectedItem flow: --- $it  ")
    }
}