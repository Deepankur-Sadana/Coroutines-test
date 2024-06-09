package com.lukaslechner.coroutineusecasesonandroid.playground.flow.concurrency

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

suspend fun main(): Unit = coroutineScope {
    val flow = MutableStateFlow(0)

    //
    launch {
        flow.collect{
            println("A coll $it")
        }
    }
    launch {
        flow.collect{
            println("B coll $it")
            delay(100)
        }
    }

    launch {
        val timeToEmit = measureTimeMillis {
            repeat(5) {
                flow.emit(it)
                delay(10)
            }
        }
        println("Time to emit all value :- $timeToEmit ms ")
    }
}