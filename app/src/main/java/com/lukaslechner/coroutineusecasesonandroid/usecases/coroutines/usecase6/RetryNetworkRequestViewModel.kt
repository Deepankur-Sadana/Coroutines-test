package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase6

import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.MockApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.security.spec.ECField
import java.util.Stack

class RetryNetworkRequestViewModel(
    private val api: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun performNetworkRequest() {
        uiState.value = UiState.Loading

        val numberOFRetries = 2
        viewModelScope.launch {
            try {
                retry(numberOFRetries) {
                    loadRecentAndroidVersions()
                }
            } catch (e: Exception) {
                uiState.value = UiState.Error("error ")
                Timber.e("outer Exception $e")

            }

        }
    }

    private suspend fun <T> retry(
        numberOfRetries: Int,
        initialDelayMillis: Long = 100L,
        maxDelayMillis: Long = Long.MAX_VALUE,
        factor: Double = 2.0,
        block: suspend () -> T,
    ): T {
        var delay = initialDelayMillis
        repeat(numberOfRetries) { count ->
            try {
                return block()
            } catch (e: Exception) {
                Timber.e("catching in retry $e")
            }
            delay(delay)
            delay *= factor.toLong().coerceAtMost(maxDelayMillis)
        }
        return block()
    }

    private suspend fun loadRecentAndroidVersions() {
        val recentAndroidVersions = api.getRecentAndroidVersions()
        uiState.value = UiState.Success(recentAndroidVersions)
    }

}