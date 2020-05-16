package com.titut.inventory.ui.friends

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.titut.inventory.db.InventoryDatabase
import com.titut.inventory.repository.FriendRepository
import javax.inject.Inject

class FriendsViewModel @Inject constructor(
    application: Application,
    testString: String
) : ViewModel() {

    private var friendRepository: FriendRepository

    init {
        val friendDao = InventoryDatabase.getInstance(application, viewModelScope).friendDao()
        friendRepository = FriendRepository(friendDao)

        println("@@@@ FriendsViewModel $testString")
    }

    fun getFriends() = friendRepository.getFriends()

    fun getFriendsWithTools() = friendRepository.getFriendsWithTools()
}