package com.lukaslechner.coroutineusecasesonandroid.playground.flow.cancellation

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import java.math.BigInteger
import kotlin.coroutines.EmptyCoroutineContext

suspend fun main() {
    val scope = CoroutineScope(EmptyCoroutineContext)
    scope.launch {
        flowOf(1,2,3)
            .onCompletion {throwable ->
                println("on completion $throwable")
                if(throwable is CancellationException) {
                    println("Flow got cancelled")
                }
            }
            .cancellable()
            .collect{
                println("Collecting $it")
                if (it == 2)
                    cancel()
            }
    }.join()
}

//private fun intFlow() = flow {
//    emit(1)
//    emit(2)
//    println("Before factorial")
//    calculateFactorialOf(1000)
//    println("After factorial")
//    emit(3)
//}
//
//private suspend fun calculateFactorialOf(number: Int) : BigInteger = coroutineScope{
//    var fact = BigInteger.ONE
//    for (i in 2..number){
//        fact *= i.toBigInteger()
//        currentCoroutineContext().ensureActive()
//    }
//    fact
//}