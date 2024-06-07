package com.lukaslechner.coroutineusecasesonandroid.playground.flow.basics

import com.lukaslechner.coroutineusecasesonandroid.playground.utils.printWithTimePassed
import java.math.BigInteger

fun main() {
    val startTime = System.currentTimeMillis()
    calculateFactorialOfList(6).forEach {
        printWithTimePassed("item $it", startTime)
    }
}

fun calculateFactorialOfList(number: Int) : List<BigInteger> = buildList {
    var factorial = BigInteger.ONE
    for (i in 2..number) {
        Thread.sleep(10)
        factorial *=i.toBigInteger()
        add(factorial)
    }
}
