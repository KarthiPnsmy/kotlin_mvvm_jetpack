package com.titut.inventory.di

import android.app.Application
import com.titut.inventory.db.InventoryDatabase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class DbModule {

    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)

    @Singleton
    @Provides
    fun provideDb(application: Application, scope: CoroutineScope) = InventoryDatabase.getInstance(application, scope)

    @Singleton
    @Provides
    fun provideToolDao(db: InventoryDatabase) = db.toolDao()

    @Singleton
    @Provides
    fun provideFriendDao(db: InventoryDatabase) = db.friendDao()
}