package com.titut.inventory.repository

import android.app.Application
import com.titut.inventory.db.InventoryDatabase
import com.titut.inventory.db.dao.FriendDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext


class FriendRepository(application: Application) : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var friendDao: FriendDao?

    init {
        val db = InventoryDatabase.getDatabase(application)
        friendDao = db.friendDao()
    }

    fun getFriends() = friendDao?.getAllFriends()

    fun getFriendsWithTools() = friendDao?.getFriendsWithTools()

    fun getFriendsWithToolsByFriend(id: Long) = friendDao?.getFriendsWithToolsByFriend(id)
}
