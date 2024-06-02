package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase3

import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.MockApi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class PerformNetworkRequestsConcurrentlyViewModel(
    private val mockApi: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun performNetworkRequestsSequentially() {
        uiState.value = UiState.Loading
        viewModelScope.launch {
            val oreoFeatures = mockApi.getAndroidVersionFeatures(27)
            val pieFeatures = mockApi.getAndroidVersionFeatures(27)
            val android10Features = mockApi.getAndroidVersionFeatures(27)
            val list = listOf(oreoFeatures, pieFeatures, android10Features)
            uiState.value = UiState.Success(list)
        }
    }

    fun performNetworkRequestsConcurrently() {
        uiState.value = UiState.Loading
        val oreoFeattdeferred = viewModelScope.async {
            mockApi.getAndroidVersionFeatures(27)
        }

        val pieFeatdeferred = viewModelScope.async {
            mockApi.getAndroidVersionFeatures(28)
        }

        val and10Featdeferred = viewModelScope.async {
            mockApi.getAndroidVersionFeatures(29)
        }
        viewModelScope.launch {
            val all = awaitAll(oreoFeattdeferred, pieFeatdeferred, and10Featdeferred)

            listOf(
                oreoFeattdeferred.await(),
                pieFeatdeferred.await(),
                and10Featdeferred.await()
            )

            uiState.value = UiState.Success(all)

        }

    }
}