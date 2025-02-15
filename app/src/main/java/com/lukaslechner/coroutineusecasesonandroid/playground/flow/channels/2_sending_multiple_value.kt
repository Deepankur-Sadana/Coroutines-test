package com.lukaslechner.coroutineusecasesonandroid.playground.flow.channels

import kotlinx.coroutines.async
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
    val channel = produce<Int> {
        println("Sending 10")
        send(10)
        println("Sending 20")
        send(20)
    }

    launch {
        channel.consumeEach {
            println("recieved $it")
        }
    }
}