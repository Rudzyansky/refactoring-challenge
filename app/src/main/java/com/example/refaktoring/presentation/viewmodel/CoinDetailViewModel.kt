package com.example.refaktoring.presentation.viewmodel

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import com.example.refaktoring.domain.CoinPriceInfoDomain
import com.example.refaktoring.domain.CoinRepository
import com.example.refaktoring.presentation.fragment.CoinDetailFragmentArgs
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.emitAll

class CoinDetailViewModel(
    private val coinRepository: CoinRepository
) : ViewModel() {

    private lateinit var _detailInfo: MutableSharedFlow<CoinPriceInfoDomain>
    val detailInfo by lazy { _detailInfo.asSharedFlow() }

    fun setup(args: CoinDetailFragmentArgs, lifecycleScope: LifecycleCoroutineScope) {
        _detailInfo = MutableSharedFlow(1)
        lifecycleScope.launchWhenStarted {
            _detailInfo.emitAll(coinRepository.getDetailInfo(args.fromSymbol))
        }
    }
}