package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase13

import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.MockApi
import kotlinx.coroutines.*
import timber.log.Timber

class ExceptionHandlingViewModel(
    private val api: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun handleExceptionWithTryCatch() {
        uiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                api.getAndroidVersionFeatures(27).features
            } catch (e: Exception) {
                uiState.value = UiState.Error(e.message ?: "")
            }
        }

    }

    fun handleWithCoroutineExceptionHandler() {
        uiState.value = UiState.Loading
        val exceptionHandler =
            CoroutineExceptionHandler { context, throwable ->
                uiState.value = UiState.Error(throwable.message ?: "Error")
            }

        viewModelScope.launch(exceptionHandler) {
            api.getAndroidVersionFeatures(27)
        }

    }

    fun showResultsEvenIfChildCoroutineFails() {
        val exceptionHandler =
            CoroutineExceptionHandler { context, throwable ->
                uiState.value = UiState.Error(throwable.message ?: "Error")
            }

        uiState.value = UiState.Loading
        viewModelScope.launch(exceptionHandler) {
            val deffered1 = async {
                api.getAndroidVersionFeatures(27)
            }
            val deffered2 = async {
                api.getAndroidVersionFeatures(28)
            }
            val deffered3 = async {
                api.getAndroidVersionFeatures(29)
            }
            val feature1 = try {
                deffered1.await()
            } catch (e: Exception) {
                null
            }
            val feature2 = try {
                deffered2.await()
            } catch (e: Exception) {
                null
            }
            val feature3 = try {
                deffered3.await()
            } catch (e: Exception) {
                null
            }
            val list = listOfNotNull(feature1, feature2, feature3)
            uiState.value = UiState.Success(list)
        }
    }
}