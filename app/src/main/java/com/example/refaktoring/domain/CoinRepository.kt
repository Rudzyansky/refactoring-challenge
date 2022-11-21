package com.example.refaktoring.domain

import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    fun getDetailInfo(fromSymbol: String): Flow<CoinPriceInfoDomain>

    fun getPriceList(): Flow<List<CoinPriceInfoDomain>>

    suspend fun insertPriceList(priceList: List<CoinPriceInfoDomain>)
}