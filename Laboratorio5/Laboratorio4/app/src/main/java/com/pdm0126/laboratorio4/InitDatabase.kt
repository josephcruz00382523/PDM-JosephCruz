package com.pdm0126.laboratorio4

import android.app.Application
import androidx.room.Room
import com.pdm0126.laboratorio4.Data.AppDatabase

class InitDatabase : Application() {
    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "AppDatabase"
        ).fallbackToDestructiveMigration(false).build()
    }
}
