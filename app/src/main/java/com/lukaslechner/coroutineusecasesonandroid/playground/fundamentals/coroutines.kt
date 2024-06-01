package com.lukaslechner.coroutineusecasesonandroid.playground.fundamentals

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("main starts")
    joinAll(
        launch { coroutine(1, 500) },
        launch { coroutine(2, 300) }
    )
    println("main ends")
}

suspend fun coroutine(number : Int, delay : Long) {
    println("Coroutine number $number starts")
    delay(delay)
    println("Coroutine number $number finished")
}