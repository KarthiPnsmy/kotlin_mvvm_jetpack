package com.titut.inventory.di

import com.titut.inventory.ui.detail.DetailFragment
import com.titut.inventory.ui.friends.FriendsFragment
import com.titut.inventory.ui.tools.ToolsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindToolsFragment():ToolsFragment

    @ContributesAndroidInjector
    abstract fun bindFriendsFragment():FriendsFragment

    @ContributesAndroidInjector
    abstract fun bindDetailFragment():DetailFragment
}
