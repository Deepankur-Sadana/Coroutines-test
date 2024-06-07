package com.lukaslechner.coroutineusecasesonandroid.playground.flow.terminal_operators

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.toSet
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    val flow = flow<Int> {

        for (i in 1..10) {
            delay(10)
            emit(i)
            println("Emitting I $i")
//            if (i == 2) break
        }


    }
    val scope = CoroutineScope((Job()))
    runBlocking {
        val item = flow.fold(5) { accumulator, emittedItem ->
            accumulator + emittedItem
        }
        println("collecting $item")
    }

}