package com.lukaslechner.coroutineusecasesonandroid.playground.structuredconcurrency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


fun main() {
    val scopeJob = Job()
    val scope = CoroutineScope(Dispatchers.Default + scopeJob)

    val passedJob = Job()
    val coroutineJob = scope.launch {
        println("coroutine started")
        delay(1000)
    }
    Thread.sleep(1000)

    println("passedJob $passedJob, coroutineJob $coroutineJob")
    passedJob.children.forEach {child ->
        println("passedjob child $child")
    }
    println("is coroutine job child of scope job " +
            "${scopeJob.children.contains(coroutineJob)}")
    println("....")
    coroutineJob.children.forEach {child ->
        println("$child")
    }

    print("coroutineJob $coroutineJob")
}
class c : CoroutineContext{
    override fun <R> fold(initial: R, operation: (R, CoroutineContext.Element) -> R): R {
        TODO("Not yet implemented")
    }

    override fun <E : CoroutineContext.Element> get(key: CoroutineContext.Key<E>): E? {
        TODO("Not yet implemented")
    }

    override fun minusKey(key: CoroutineContext.Key<*>): CoroutineContext {
        TODO("Not yet implemented")
    }

}