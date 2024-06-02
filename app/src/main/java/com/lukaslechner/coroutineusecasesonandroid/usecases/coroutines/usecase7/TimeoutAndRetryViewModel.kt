package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase7

import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.MockApi
import com.lukaslechner.coroutineusecasesonandroid.mock.VersionFeatures
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import timber.log.Timber

class TimeoutAndRetryViewModel(
    private val api: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun performNetworkRequest() {
        uiState.value = UiState.Loading
        val numberOfRetries = 2
        val timeout = 1000L

        // TODO: Exercise 3
        // switch to branch "coroutine_course_full" to see solution

        // run api.getAndroidVersionFeatures(27) and api.getAndroidVersionFeatures(28) in parallel
        //exponential backoff
        val async1 = viewModelScope.async {
            retryWithTimeout(numberOfRetries, 100, timeoutDuration = timeout) {
                getVersionFeatures(27)
            }
        }

        val async2 = viewModelScope.async {
            retryWithTimeout(numberOfRetries, 100, timeoutDuration = timeout) {
                getVersionFeatures(28)
            }
        }
        viewModelScope.launch {
            val features = listOf( async1.await() , async2.await())
            uiState.value = UiState.Success(features)
        }

    }

    private suspend  fun <T> retryWithTimeout(
        numberOfRetries: Int,
        initialDelayMillis: Long = 100L,
        maxBackOffDelayedTime: Long = Long.MAX_VALUE,
        factor: Double = 2.0,
        timeoutDuration: Long = 1500,
        block: suspend () -> T
    ) :T {
        var currDelay = initialDelayMillis
        repeat(numberOfRetries) { attempt ->
            try {
                withTimeout(timeoutDuration) {
                    return@withTimeout block()
                }
            } catch (e: TimeoutCancellationException) {
                Timber.e("timeout exception $block $e")
            } catch (e: Exception) {
                Timber.e("generic exception $block $e")

            }
            currDelay *= factor.toLong().coerceAtMost(maxBackOffDelayedTime)
            delay(currDelay)
        }
        return block()
    }

    private suspend fun getVersionFeatures(version: Int): VersionFeatures {
        return api.getAndroidVersionFeatures(version)
    }

}