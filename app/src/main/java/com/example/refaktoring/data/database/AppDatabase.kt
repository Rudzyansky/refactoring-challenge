package com.example.refaktoring.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.refaktoring.data.pojo.CoinPriceInfo

@Database(entities = [CoinPriceInfo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun coinPriceInfoDao(): CoinPriceInfoDao

    companion object {
        private const val DB_NAME = "main.db"
        fun newInstance(context: Context) = Room
            .databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()
    }
}
