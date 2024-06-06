package com.lukaslechner.coroutineusecasesonandroid.playground.exceptionhandling

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun main() {
    val scope = CoroutineScope(Job())
    println(",,,,,,,,")
    scope.launch {
        try {

            launch {  }
            launch {
                throwEx()
            }
        } catch (e: Exception) {
            println("caught exception")
        }
    }
    Thread.sleep(1000)
}

fun throwEx() {
    throw RuntimeException("asdfghjkl")
}