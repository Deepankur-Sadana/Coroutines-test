package com.lukaslechner.coroutineusecasesonandroid.playground.flow.exceptionhandling

import android.accounts.NetworkErrorException
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
    launch {
        stocksFlow()
            .catch { throwable ->
                println("handle exception in catch $throwable")
            }
            .collect { stockData ->
                println("Collect $stockData")
            }
    }
}

private fun stocksFlow(): Flow<String> =
    flow {
        repeat(5) { index ->
            delay(1000)
            if (index < 4) {
                emit("New Stock data  $index")
            } else {
                throw NetworkErrorException("n/w request failed")
            }
        }
    }.retry(retries = 3) {
        println("Retrying on $it")
        true
    }