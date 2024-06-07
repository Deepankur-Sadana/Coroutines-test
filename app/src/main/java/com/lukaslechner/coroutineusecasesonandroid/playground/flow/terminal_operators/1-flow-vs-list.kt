package com.lukaslechner.coroutineusecasesonandroid.playground.flow.terminal_operators

import kotlinx.coroutines.flow.flow

fun main() {
    val flow = flow {
        kotlinx.coroutines.delay(100)
        emit(1)
        kotlinx.coroutines.delay(100)
        emit(2)
    }
}