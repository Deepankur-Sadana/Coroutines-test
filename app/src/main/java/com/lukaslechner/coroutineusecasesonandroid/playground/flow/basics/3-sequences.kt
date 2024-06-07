package com.lukaslechner.coroutineusecasesonandroid.playground.flow.basics

import com.lukaslechner.coroutineusecasesonandroid.playground.utils.printWithTimePassed
import kotlinx.coroutines.delay
import java.math.BigInteger

fun main() {
    val startTime = System.currentTimeMillis()
    calculateFactorialOfSequence(6).forEach {
        printWithTimePassed("item $it", startTime)
    }
}

fun calculateFactorialOfSequence(number: Int) : Sequence<BigInteger> = sequence {
    var factorial = BigInteger.ONE
    for (i in 2..number) {
//        Thread.sleep(10)
//        delay(100)
        factorial *=i.toBigInteger()
        yield(factorial)
    }
}