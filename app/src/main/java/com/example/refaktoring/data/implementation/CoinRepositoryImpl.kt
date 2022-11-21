package com.example.refaktoring.data.implementation

import com.example.refaktoring.data.database.CoinPriceInfoDao
import com.example.refaktoring.domain.CoinPriceInfoDomain
import com.example.refaktoring.domain.CoinRepository
import kotlinx.coroutines.flow.Flow

class CoinRepositoryImpl(private val dao: CoinPriceInfoDao) : CoinRepository {

    override fun getDetailInfo(fromSymbol: String): Flow<CoinPriceInfoDomain> {
        return dao.getPriceInfoAboutCoin(fromSymbol)
    }

    override fun getPriceList(): Flow<List<CoinPriceInfoDomain>> {
        return dao.getPriceList()
    }

    override suspend fun insertPriceList(priceList: List<CoinPriceInfoDomain>) {
        dao.insertPriceList(priceList)
    }
}