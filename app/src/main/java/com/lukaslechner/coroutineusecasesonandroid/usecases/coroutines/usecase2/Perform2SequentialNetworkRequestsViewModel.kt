package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase2

import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.MockApi
import kotlinx.coroutines.launch

class Perform2SequentialNetworkRequestsViewModel(
    private val mockApi: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun perform2SequentialNetworkRequest() {
        uiState.value = UiState.Loading
        viewModelScope.launch {
            val recentVersions = mockApi.getRecentAndroidVersions()
            val mostRecentVersion = recentVersions.last()
            val featureOfMostRecentVersions = mockApi
                .getAndroidVersionFeatures(mostRecentVersion.apiLevel)
            uiState.value = UiState.Success(featureOfMostRecentVersions)
        }
    }
}