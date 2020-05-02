package com.titut.inventory.ui.friends

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.titut.inventory.db.InventoryDatabase
import com.titut.inventory.repository.FriendRepository

class FriendsViewModel(application: Application) : AndroidViewModel(application) {

    private var friendRepository: FriendRepository

    init {
        val friendDao = InventoryDatabase.getInstance(application, viewModelScope).friendDao()
        friendRepository = FriendRepository(friendDao)
    }

    fun getFriends() = friendRepository.getFriends()

    fun getFriendsWithTools() = friendRepository.getFriendsWithTools()
}