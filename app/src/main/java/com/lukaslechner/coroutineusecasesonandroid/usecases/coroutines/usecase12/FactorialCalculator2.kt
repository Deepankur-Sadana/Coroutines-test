package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase12

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.withContext
import java.math.BigInteger

class FactorialCalculator2(
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    suspend fun calculateFactorial(
        factorialOf: Int,
        numberOfCoroutines: Int
    ): BigInteger {

        // TODO: create sub range list *on background thread*
        val mul = withContext(defaultDispatcher) {
            val subRanges = createSubRangeList(factorialOf, numberOfCoroutines)
            val res = subRanges.map {
                 async {
                    calculateFactorialOfSubRange(it)
                }
            }.awaitAll()

             multiplyResultOfAllSubRanges(res)
        }
        return mul
    }

    private suspend fun multiplyResultOfAllSubRanges(list: List<BigInteger>): BigInteger {
        var result = BigInteger.ONE
        withContext(defaultDispatcher) {
            list.forEach { result *= it }
        }
        return result
    }

    // TODO: execute on background thread
    private suspend fun calculateFactorialOfSubRange(
        subRange: SubRange
    ): BigInteger = withContext(defaultDispatcher) {
        var factorial = BigInteger.ONE
        for (i in subRange.start..subRange.end) {
            factorial = factorial.multiply(BigInteger.valueOf(i.toLong()))
        }
        factorial
    }

    private fun createSubRangeList(
        factorialOf: Int,
        numberOfSubRanges: Int
    ): List<SubRange> {
        val rangesList = mutableListOf<SubRange>()
        val partitionSize = factorialOf / numberOfSubRanges
        var lastStart = 1
        for (i in 1..numberOfSubRanges) {
            val next =
                if (i == numberOfSubRanges)
                    factorialOf
                else lastStart + partitionSize
            rangesList.add(SubRange(lastStart, next))
            lastStart = next + 1
        }
        return rangesList
    }
}


