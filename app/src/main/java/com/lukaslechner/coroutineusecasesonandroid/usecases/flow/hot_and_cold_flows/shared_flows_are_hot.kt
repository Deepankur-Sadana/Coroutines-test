package com.lukaslechner.coroutineusecasesonandroid.usecases.flow.hot_and_cold_flows

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit  {
    val sharedFlow = MutableSharedFlow<Int>()
    val scope = CoroutineScope(Dispatchers.Default)
    scope.launch {
        repeat(5) {
            println("emitting $it")
            sharedFlow.emit(it)
            delay(200)
        }
    }
    println("thread ${Thread.currentThread().name}")
    Thread.sleep(400)

    scope.launch {
        sharedFlow.collect {
            println("collecting $it")
        }
    }
    Thread.sleep(4000)

}