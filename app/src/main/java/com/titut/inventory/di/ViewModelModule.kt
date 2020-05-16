package com.titut.inventory.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.titut.inventory.ui.friends.FriendsViewModel
import com.titut.inventory.ui.tools.ToolsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ToolsViewModel::class)
    abstract fun bindToolsViewModel(toolsViewModel: ToolsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FriendsViewModel::class)
    abstract fun bindFriendsViewModel(friendsViewModel: FriendsViewModel): ViewModel

}