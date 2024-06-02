package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase4

import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.MockApi
import com.lukaslechner.coroutineusecasesonandroid.mock.VersionFeatures
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class VariableAmountOfNetworkRequestsViewModel(
    private val mockApi: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun performNetworkRequestsSequentially() {
        uiState.value = UiState.Loading
        viewModelScope.launch {
            val recentVersions = mockApi.getRecentAndroidVersions()
            val versionFeatures = recentVersions.map { androidVersion ->
                mockApi.getAndroidVersionFeatures(androidVersion.apiLevel)
            }
            uiState.value = UiState.Success(versionFeatures)
        }
    }

    fun performNetworkRequestsConcurrently() {
        uiState.value = UiState.Loading
        viewModelScope.launch {
            val recentVersions = mockApi.getRecentAndroidVersions()
//            val list = ArrayList<Deferred<VersionFeatures>>()
            val res = recentVersions.map { version ->
                async {
                    mockApi.getAndroidVersionFeatures(version.apiLevel)
                }
            }.awaitAll()
            val featList = ArrayList<VersionFeatures>()

            uiState.value = UiState.Success(res)
        }

    }
}