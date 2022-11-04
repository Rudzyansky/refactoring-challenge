package com.example.refaktoring.di

import com.example.refaktoring.data.api.ApiFactory
import com.example.refaktoring.data.api.ApiFactory.apiService
import com.example.refaktoring.data.database.AppDatabase
import com.example.refaktoring.presentation.viewmodel.CoinViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    single { AppDatabase.newInstance(androidContext()) }
    single { get<AppDatabase>().coinPriceInfoDao() }

    single { ApiFactory.newRetrofitInstance() }
    single { get<Retrofit>().apiService() }

//    single { SettingsRepositoryRoom(get()) } bind SettingsRepository::class
//    single { GuessTheWordRepositoryInMemory() } bind GuessTheWordRepository::class

    viewModel { CoinViewModel(get(), get()) }
}