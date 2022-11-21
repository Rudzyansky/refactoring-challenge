package com.example.refaktoring.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.refaktoring.domain.CoinPriceInfoDomain
import com.example.refaktoring.domain.CoinRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch

class CoinPriceListViewModel(
    private val coinRepository: CoinRepository
) : ViewModel() {

    private val _priceList = MutableSharedFlow<List<CoinPriceInfoDomain>>(1)
    val priceList = _priceList.asSharedFlow()

    init {
        viewModelScope.launch {
            _priceList.emitAll(coinRepository.getPriceList())
        }
    }
}