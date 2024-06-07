package com.lukaslechner.coroutineusecasesonandroid.playground.flow.basics

import com.lukaslechner.coroutineusecasesonandroid.playground.utils.printWithTimePassed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.math.BigInteger

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    launch {
        calculateFactorialOfFlow(6).collect {
            printWithTimePassed("item $it", startTime)
        }
    }
    println("Ready for more work")
}

fun calculateFactorialOfFlow(number: Int): Flow<BigInteger> = flow {
    var factorial = BigInteger.ONE
    for (i in 2..number) {
        delay(10)
        factorial *= i.toBigInteger()
        emit(factorial)
    }
}.flowOn(Dispatchers.Default)