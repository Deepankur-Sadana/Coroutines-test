package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase10

import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.math.BigInteger
import kotlin.system.measureTimeMillis

class CalculationInBackgroundViewModel : BaseViewModel<UiState>() {

    fun performCalculation(factorialOf: Int) {
        uiState.value = UiState.Loading
        viewModelScope.launch() {
            Timber.d("coroutine context $coroutineContext")
            var result = BigInteger.ONE
            val computationDuration = measureTimeMillis {
                result = calculateFactorial(factorialOf)

            }
            var resultString = ""
            val stringConversionDuration = measureTimeMillis {
                withContext(Dispatchers.Default) {
                    resultString = result.toString()
                }
            }

            uiState.value =
                UiState.Success(resultString, computationDuration, stringConversionDuration)

        }
    }

    private suspend fun calculateFactorial(number: Int) = withContext(Dispatchers.Default) {
        var factorial = BigInteger.ONE
        Timber.d("calculate factorial coroutine context $coroutineContext")

        for (i in number downTo 1) {
            factorial *= i.toBigInteger()
        }
        factorial
    }


}