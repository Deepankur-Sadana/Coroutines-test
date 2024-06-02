package com.lukaslechner.coroutineusecasesonandroid.playground.coroutinebuilders

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    val startTime = System.currentTimeMillis()

    val deferred1 = async(start = CoroutineStart.LAZY) {
        val result1 = networkCall(1)
        println("result recieved for 1 ${result1} after ${elapsedMillis(startTime)}")
        result1
    }

    deferred1.start()

    val deferred2 = async {
        val result2 = networkCall(2)
        println("result recieved for 2 ${result2} after ${elapsedMillis(startTime)}")
        result2

    }

    val list = deferred1.await() + deferred2.await()

    println("list received $list ${elapsedMillis(startTime)} ")

    println("ending ${elapsedMillis(startTime)}")
}

suspend fun networkCall(number: Int): String {
    delay(500)
    return "Result $number"
}

fun elapsedMillis(startTime : Long): Long {
    return System.currentTimeMillis() - startTime
}
