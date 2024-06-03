package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase12

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import java.math.BigInteger

class FactorialCalculator(
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    suspend fun calculateFactorial(
        factorialOf: Int,
        numberOfCoroutines: Int,
        coroutineScope: CoroutineScope
    ): BigInteger {

        // TODO: create sub range list *on background thread*
        val subRanges = createSubRangeList(factorialOf, numberOfCoroutines)
        val list  = ArrayList<Deferred<BigInteger>>()
        for (i in subRanges.indices) {
            val range = subRanges[i]
            val deferredJob = coroutineScope.async {
                calculateFactorialOfSubRange(range)
            }
            list.add(deferredJob)
        }

        // TODO: calculate factorial of each subrange in separate coroutine
        // use calculateFactorialOfSubRange(subRange) therefore


        // TODO: create factorial result by multiplying all sub-results and return this
        // result
        val result = multiplyResultOfAllSubRanges(list.awaitAll())

        return result
    }

    private suspend fun multiplyResultOfAllSubRanges(list: List<BigInteger>) : BigInteger{
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
        val quotient = factorialOf.div(numberOfSubRanges)
        val rangesList = mutableListOf<SubRange>()

        var curStartIndex = 1
        repeat(numberOfSubRanges - 1) {
            rangesList.add(
                SubRange(
                    curStartIndex,
                    curStartIndex + (quotient - 1)
                )
            )
            curStartIndex += quotient
        }
        rangesList.add(SubRange(curStartIndex, factorialOf))
        return rangesList
    }
}


data class SubRange(val start: Int, val end: Int)