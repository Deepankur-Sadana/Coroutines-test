package com.lukaslechner.coroutineusecasesonandroid.playground.flow.concurrency

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

suspend fun main(): Unit = coroutineScope {
    var flow: Flow<Int>? = null

    flow = flow {
        repeat(5) {
            println("$it cook start  , ${Thread.currentThread().name}")
            delay(100)
            println("$it cook end ")
            emit(it)
        }
    }.buffer()

    delay(10)
    launch {
        flow.collect {
            launch {
                println("$it eat start , ${Thread.currentThread().name}")
                delay(300)
                println("$it eat end ")
            }
        }
    }

//    delay(5000)
}

suspend fun test() {
    flowOf("A", "B", "C")
        .onEach { println("1$it , ${Thread.currentThread().name}") }
//        .buffer()
        .collect { println("2$it") }

}