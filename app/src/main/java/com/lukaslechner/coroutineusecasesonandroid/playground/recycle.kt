package com.lukaslechner.coroutineusecasesonandroid.playground

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import kotlin.coroutines.EmptyCoroutineContext

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

val list = ArrayList<Int>()
fun maxLevelSum(root: TreeNode?): Int {
    dfs(root, 0)
    return list.maxOf { it }
}

fun dfs(root: TreeNode?, level: Int) {
    if (root == null) return
    if (list.size < level + 1) {
        list.add(0)
    }
    list[level] += root.`val`
    dfs(root.left, level + 1)
    dfs(root.right, level + 1)
}


fun maisdasdn() {
    val flow = flow {
        emit(1)
        delay(100)
        emit(2)
        delay(100)
        emit(3)
    }

    val scope = CoroutineScope(Job())
    scope.launch {
        flow.collect {
            println("collecting $it")
            println("....$it")
        }
    }
}

fun main() {
//    val producer = MutableStateFlow<Int>(0)
    val scope = CoroutineScope(EmptyCoroutineContext)
    scope.launch {
        for (i in 1..4) {
            println("emitting $i")
            itemEmitted(i, this)
            delay(100)
        }
    }
    Thread.sleep(5000)
}

val delay = 500L
var job: Job? = null

fun itemEmitted(
    item: Int,
    scope: CoroutineScope
) {
    job?.cancel()
    job = null
    job = fireAfterDebounce(scope, item)
}

fun fireAfterDebounce(scope: CoroutineScope, item: Int?): Job {
    val job = scope.launch(Dispatchers.IO) {
        delay(delay)
        ensureActive()
        println("fired with $item with Debounced ")
    }
    job.invokeOnCompletion {
        println("job.invokeOnCompletion $item :- $it ")
    }
    return job
}