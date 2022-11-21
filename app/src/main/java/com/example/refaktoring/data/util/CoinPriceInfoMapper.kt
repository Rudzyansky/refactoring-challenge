package com.example.refaktoring.data.util

import com.example.refaktoring.data.pojo.CoinPriceInfo
import com.example.refaktoring.domain.CoinPriceInfoDomain

internal fun domainCoinPriceInfo(info: CoinPriceInfo): CoinPriceInfoDomain {
    return CoinPriceInfoDomain(
        price = info.price,
        lowDay = info.lowDay,
        highDay = info.highDay,
        lastMarket = info.lastMarket,
        lastUpdate = info.lastUpdate,
        fromSymbol = info.fromSymbol,
        toSymbol = info.toSymbol,
        imageUrl = info.imageUrl,
    )
}