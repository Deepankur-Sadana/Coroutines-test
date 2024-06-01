package com.lukaslechner.coroutineusecasesonandroid.playground.fundamentals

import kotlinx.coroutines.delay

fun main() {
    println("Hello World")
}
suspend fun coroutine1Test(number: Int, delay : Long) {
    println("coroutine $number starts work")
    delay(delay)
    println("coroutine $number finished")
}