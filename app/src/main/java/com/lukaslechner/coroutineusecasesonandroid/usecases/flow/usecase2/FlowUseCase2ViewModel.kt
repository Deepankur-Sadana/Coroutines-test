package com.lukaslechner.coroutineusecasesonandroid.usecases.flow.usecase2

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.playground.list
import com.lukaslechner.coroutineusecasesonandroid.usecases.flow.mock.Stock
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.take

class FlowUseCase2ViewModel(
    private val stockPriceDataSource: StockPriceDataSource,
    val defaultDispatcher: CoroutineDispatcher
) : BaseViewModel<UiState>() {

    /*

    Flow exercise 1 Goals
        1) only update stock list when Alphabet(Google) (stock.name ="Alphabet (Google)") stock price is > 2300$
        2) only show stocks of "United States" (stock.country == "United States")
        3) show the correct rank (stock.rank) within "United States", not the world wide rank
        4) filter out Apple  (stock.name ="Apple") and Microsoft (stock.name ="Microsoft"), so that Google is number one
        5) only show company if it is one of the biggest 10 companies of the "United States" (stock.rank <= 10)
        6) stop flow collection after 10 emissions from the dataSource
        7) log out the number of the current emission so that we can check if flow collection stops after exactly 10 emissions
        8) Perform all flow processing on a background thread

     */

    val currentStockPriceAsLiveData = getStocks()

    private fun getStocks(): LiveData<UiState> {
        return stockPriceDataSource.latestStockList
            .take(10)
            .onEach { println("FLOW logging $it") }
            .filter { list ->
                var emit = false
                list.forEach {
                    if(it.name == "Alphabet (Google)") {
                        emit = it.currentPrice >= 2300
                    }
                }
                emit
            }
            .map { list ->//UC 2
                list.filter { it.country == "United States" }
            }
            .map {list ->
                list.filterNot { it.name == "Microsoft" || it.name == "Apple" }
            }
            .map { it.take(10) }
            .map { UiState.Success(it) as UiState }
            .onStart { UiState.Loading }
            .asLiveData(Dispatchers.Default) // UC8
    }
}