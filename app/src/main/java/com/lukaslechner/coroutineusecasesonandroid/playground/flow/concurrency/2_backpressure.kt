package com.lukaslechner.coroutineusecasesonandroid.playground.flow.concurrency

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow

suspend fun main(): Unit = coroutineScope {
    val flow: Flow<Int>?

    flow = flow {
        repeat(5) {
            println("$it cook start  , ${Thread.currentThread().name}")
            delay(100)
            println("$it cook end ")
            emit(it)
        }
    }.buffer(capacity = UNLIMITED, onBufferOverflow = BufferOverflow.SUSPEND)

    flow.collect {
        println("$it eat start , ${Thread.currentThread().name}")
        delay(300)
        println("$it eat end ")
    }
}

