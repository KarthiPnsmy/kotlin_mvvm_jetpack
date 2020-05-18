package com.titut.inventory.ui.friends

import androidx.lifecycle.ViewModel
import com.titut.inventory.repository.FriendRepository

class FriendsViewModel(private var friendRepository: FriendRepository) : ViewModel() {

    fun getFriends() = friendRepository.getFriends()

    fun getFriendsWithTools() = friendRepository.getFriendsWithTools()
}