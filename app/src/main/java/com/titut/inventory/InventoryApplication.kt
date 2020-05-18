package com.titut.inventory

import android.app.Application
import com.titut.inventory.di.dbModule
import com.titut.inventory.di.preferenceModule
import com.titut.inventory.di.repositoryModule
import com.titut.inventory.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class InventoryApplication:Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@InventoryApplication)
            modules(listOf(dbModule, repositoryModule, viewModelModule, preferenceModule))
        }
    }
}