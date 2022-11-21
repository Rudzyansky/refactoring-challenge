package com.example.refaktoring.domain

import com.example.refaktoring.domain.util.convertTimestampToTime

data class CoinPriceInfoDomain(

    val price: String?,
    val lowDay: String?,
    val highDay: String?,
    val lastMarket: String?,
    val lastUpdate: Long?,
    val fromSymbol: String,
    val toSymbol: String?,
    val imageUrl: String?
) {
    fun getFormattedTime(): String {
        return convertTimestampToTime(lastUpdate)
    }

    fun getFullImageUrl(): String {
        return BASE_IMAGE_URL + imageUrl
    }

    companion object {
        private const val BASE_IMAGE_URL = "https://cryptocompare.com"
    }
}
