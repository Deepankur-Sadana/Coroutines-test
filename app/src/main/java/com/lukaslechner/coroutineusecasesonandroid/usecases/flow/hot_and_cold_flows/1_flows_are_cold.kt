package com.lukaslechner.coroutineusecasesonandroid.usecases.flow.hot_and_cold_flows

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

fun coldFlow() = flow {

    println("Emitting 1")
    emit(1)
    kotlinx.coroutines.delay(1000)

    println("Emitting 2")
    emit(2)
    kotlinx.coroutines.delay(1000)


    println("Emitting 3")
    emit(3)


}

suspend fun main() : Unit = coroutineScope {
    val job = launch {
        coldFlow()
            .onCompletion {
                println("onCompletion of Collector 1 $it")
            }
            .collect {
                println("Collector 1 collects $it")
            }
    }
    delay(1500)
    job.cancelAndJoin()

}