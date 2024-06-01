package com.lukaslechner.coroutineusecasesonandroid.playground.fundamentals

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() = runBlocking {
    println("main starts")
    joinAll(
        async { threadsSwitchingCoroutine(1, 500) },
        async { threadsSwitchingCoroutine(2, 300) }
    )
    println("main ends")
}

suspend fun threadsSwitchingCoroutine(number: Int, delay: Long) {
    println("Coroutine number $number starts, ${Thread.currentThread().name}")
    delay(delay)
    withContext(Dispatchers.Default) {
        println("Coroutine number $number finished ${Thread.currentThread().name}")
    }
}