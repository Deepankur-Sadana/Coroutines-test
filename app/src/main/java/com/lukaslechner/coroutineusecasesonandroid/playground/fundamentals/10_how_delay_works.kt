package com.lukaslechner.coroutineusecasesonandroid.playground.fundamentals

import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("main starts")
    joinAll(
        launch { delayDemo(1, 500) },
        launch { delayDemo(2, 300) }
    )
    println("main ends")
}

suspend fun delayDemo(number : Int, delay : Long) {
    println("Coroutine number $number starts")
    delay(delay)
    println("Coroutine number $number finished")
}