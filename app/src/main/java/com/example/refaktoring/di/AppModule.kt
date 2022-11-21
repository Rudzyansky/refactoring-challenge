package com.example.refaktoring.di

import com.example.refaktoring.data.api.CoinsPricesApi
import com.example.refaktoring.data.api.RetrofitHelper
import com.example.refaktoring.data.database.AppDatabase
import com.example.refaktoring.data.implementation.CoinRepositoryImpl
import com.example.refaktoring.data.service.DataLoader
import com.example.refaktoring.domain.CoinRepository
import com.example.refaktoring.presentation.viewmodel.CoinDetailViewModel
import com.example.refaktoring.presentation.viewmodel.CoinPriceListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    single { AppDatabase.newInstance(androidContext()) }
    single { get<AppDatabase>().coinPriceInfoDao() }

    single { RetrofitHelper.newInstance() }
    single { get<Retrofit>().create(CoinsPricesApi::class.java) }

    single { DataLoader(get(), get()) }

    single { CoinRepositoryImpl(get()) } bind CoinRepository::class

    viewModel { CoinPriceListViewModel(get()) }
    viewModel { CoinDetailViewModel(get()) }
}