package com.titut.inventory.ui.friends

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.titut.inventory.repository.FriendRepository

class FriendsViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: FriendRepository = FriendRepository(application)

    fun getFriends() = repository.getFriends()

    fun getFriendsWithTools() = repository.getFriendsWithTools()
}