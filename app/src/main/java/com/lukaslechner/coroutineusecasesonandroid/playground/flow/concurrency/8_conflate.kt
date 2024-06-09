package com.lukaslechner.coroutineusecasesonandroid.playground.flow.concurrency

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

suspend fun main(): Unit = coroutineScope {
    val flow = flow {
        repeat(5) {
            println("$it cook start  , ${Thread.currentThread().name}")
            delay(100)
            println("$it cook end ")
            emit(it)
        }
    }.conflate()

    flow.collect {
        println("$it eat start , ${Thread.currentThread().name}")
        delay(300)
        println("$it eat end ")
    }
}