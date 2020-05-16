package com.titut.inventory.di

import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideString(): String {
        return "karthi"
    }
}