package com.example.refaktoring.data.util

import com.example.refaktoring.data.pojo.CoinPriceInfo
import com.example.refaktoring.data.pojo.CoinPriceInfoRawData
import com.google.gson.Gson

internal fun getPriceListFromRawData(
    coinPriceInfoRawData: CoinPriceInfoRawData
): List<CoinPriceInfo> {
    val result = ArrayList<CoinPriceInfo>()
    val jsonObject = coinPriceInfoRawData.coinPriceInfoJsonObject ?: return result
    val coinKeySet = jsonObject.keySet()
    for (coinKey in coinKeySet) {
        val currencyJson = jsonObject.getAsJsonObject(coinKey)
        val currencyKeySet = currencyJson.keySet()
        for (currencyKey in currencyKeySet) {
            val priceInfo = Gson().fromJson(
                currencyJson.getAsJsonObject(currencyKey),
                CoinPriceInfo::class.java
            )
            result.add(priceInfo)
        }
    }
    return result
}