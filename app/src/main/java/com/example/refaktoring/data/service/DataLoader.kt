package com.example.refaktoring.data.service

import android.util.Log
import com.example.refaktoring.data.api.CoinsPricesApi
import com.example.refaktoring.data.database.CoinPriceInfoDao
import com.example.refaktoring.data.util.domainCoinPriceInfo
import com.example.refaktoring.data.util.getPriceListFromRawData
import com.example.refaktoring.domain.CoinPriceInfoDomain
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.retry
import java.io.IOException

class DataLoader(
    private val api: CoinsPricesApi,
    private val dao: CoinPriceInfoDao
) {
    private var job: Job? = null

    init {
        start()
    }

    fun stop() {
        job?.cancel()
        job = null
    }

    suspend fun loadPriceList(): List<CoinPriceInfoDomain>? {
        return api.getTopCoinsInfo(limit = 50)
            .body()?.data?.map { it.coinInfo?.name }?.joinToString { "," }
            ?.let { api.getFullPriceList(fSyms = it).body() }
            ?.let { getPriceListFromRawData(it) }
            ?.map(::domainCoinPriceInfo)
    }

    fun start() {
        job = CoroutineScope(Dispatchers.IO).launch {
            while (true) {
                delay(10_000L)
                try {
                    val list = loadPriceList()?.also { dao.insertPriceList(it) }!!
                    Log.d("TEST_OF_LOADING_DATA", "Success: $list")
                } catch (e: IOException) {
                    Log.d("TEST_OF_LOADING_DATA", "Failure: ${e.message}")
                }
            }
        }
//        val a :Flow<Int>
//        a.retry { true }
    }
}