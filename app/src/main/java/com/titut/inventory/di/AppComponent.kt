package com.titut.inventory.di

import android.app.Application
import com.titut.inventory.InventoryApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules=[
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    AppModule::class,
    DbModule::class,
    ActivityBindingModule::class,
    ActivityBuilder::class,
    ViewModelModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: InventoryApplication)
}