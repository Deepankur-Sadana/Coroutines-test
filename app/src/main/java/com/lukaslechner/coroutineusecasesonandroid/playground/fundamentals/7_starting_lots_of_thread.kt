package com.lukaslechner.coroutineusecasesonandroid.playground.fundamentals

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

fun main () =  runBlocking<Unit> {
    var count = 0
    repeat(1_000_000) {
        thread {
            Thread.sleep(5000)
            print(".${count++}")
        }
    }

}