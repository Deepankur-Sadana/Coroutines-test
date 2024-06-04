package com.lukaslechner.coroutineusecasesonandroid.playground.structuredconcurrency

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    val alphaJob = Job()
    val global = GlobalScope.launch(alphaJob) {
        delay(1000)
    }
    alphaJob.children.forEach {
        println("alphaJobchild $it")
    }

    println("global $global")
    println("Job of global scope ${GlobalScope.coroutineContext[Job]}")
}