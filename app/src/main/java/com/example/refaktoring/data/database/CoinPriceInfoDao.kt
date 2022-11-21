package com.example.refaktoring.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.refaktoring.domain.CoinPriceInfoDomain
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinPriceInfoDao {
    @Query("SELECT * FROM full_price_list ORDER BY lastUpdate DESC")
    fun getPriceList(): Flow<List<CoinPriceInfoDomain>>

    @Query("SELECT * FROM full_price_list WHERE fromSymbol == :fSym LIMIT 1")
    fun getPriceInfoAboutCoin(fSym: String): Flow<CoinPriceInfoDomain>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPriceList(priceList: List<CoinPriceInfoDomain>)
}
