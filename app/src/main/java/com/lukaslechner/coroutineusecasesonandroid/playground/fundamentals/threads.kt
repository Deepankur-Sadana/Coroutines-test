package com.lukaslechner.coroutineusecasesonandroid.playground.fundamentals

import kotlinx.coroutines.async
import kotlinx.coroutines.delay

import kotlin.concurrent.thread

fun main() {
    println("main starts")

    threadRoutine(1, 500)
    threadRoutine(2, 300)
    println("fired methods")

    Thread.sleep(1000)

    println("main ends")
}

fun threadRoutine(number: Int, delay: Long) {
    thread {
        println("Routine number $number starts")
        Thread.sleep(delay)
        println("Routine number $number finished")
    }

}