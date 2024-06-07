package com.lukaslechner.coroutineusecasesonandroid.playground.flow.basics

import java.math.BigInteger

fun main() {

}

fun calculateFactorialOf(number: Int) : BigInteger{
    var factorial = BigInteger.ONE
    for (i in 2..number) {
        Thread.sleep(10)
        factorial *=i.toBigInteger()
    }
    return factorial
}