package com.titut.inventory.di

import android.preference.PreferenceManager
import com.titut.inventory.db.InventoryDatabase
import com.titut.inventory.repository.FriendRepository
import com.titut.inventory.repository.ToolRepository
import com.titut.inventory.ui.friends.FriendsViewModel
import com.titut.inventory.ui.tools.ToolsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dbModule = module {
    factory { CoroutineScope(Dispatchers.IO) }

    single {
        InventoryDatabase.getInstance(context = get(), coroutineScope = get())
    }

    factory { get<InventoryDatabase>().friendDao() }
    factory { get<InventoryDatabase>().toolDao() }
}

val repositoryModule = module {
    single { ToolRepository(get()) }
    single { FriendRepository(get()) }
}

val viewModelModule = module {
    viewModel { ToolsViewModel(get(), get()) }
    viewModel { FriendsViewModel(get()) }
}