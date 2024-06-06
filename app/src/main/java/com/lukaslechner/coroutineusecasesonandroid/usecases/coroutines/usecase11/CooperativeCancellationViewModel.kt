package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase11

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield
import timber.log.Timber
import java.math.BigInteger

class CooperativeCancellationViewModel(
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : ViewModel() {

     private var factorialJob : Job?= null
    fun performCalculation(factorialOf: Int) {
         factorialJob = viewModelScope.launch {
            calculateFactorial(factorialOf)
        }

        factorialJob?.invokeOnCompletion {
            Timber.d("factorialJob?.invokeOnCompletion $it")
        }
    }

    fun cancelCalculation() {
        factorialJob?.cancel()
    }

    fun uiState(): LiveData<UiState> = uiState

    private val uiState: MutableLiveData<UiState> = MutableLiveData()

    private suspend fun calculateFactorial(number: Int) = withContext(Dispatchers.Default) {
        var factorial = BigInteger.ONE
        Timber.d("calculate factorial coroutine context $coroutineContext")

        for (i in number downTo 1) {
            factorial *= i.toBigInteger()
            if (isActive.not())break
            yield()
            ensureActive()
        }
        Timber.d("calculating fact completed")
        factorial
    }
}