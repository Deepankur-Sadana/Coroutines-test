package com.lukaslechner.coroutineusecasesonandroid.playground.flow.intermidiate_operators

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.takeWhile

suspend fun main() {
    val flow = flow {
        var i = 0
        while (true) {
            emit(++i)
            println("emitting $i")
            kotlinx.coroutines.delay(100)
        }
    }


    flow
        .distinctUntilChanged()
        .collect {
            println("collecting $it")
        }
}