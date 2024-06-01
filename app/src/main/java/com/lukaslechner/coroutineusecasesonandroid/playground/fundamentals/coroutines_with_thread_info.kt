package com.lukaslechner.coroutineusecasesonandroid.playground.fundamentals

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("main starts")
    joinAll(
        launch { coroutineWithThreadInfo(1, 500) },
        launch { coroutineWithThreadInfo(2, 300) }
    )
    println("main ends")
}

suspend fun coroutineWithThreadInfo(number : Int, delay : Long) {
    println("Coroutine number $number starts, ${Thread.currentThread().name}")
    delay(delay)
    println("Coroutine number $number finished ${Thread.currentThread().name}")
}