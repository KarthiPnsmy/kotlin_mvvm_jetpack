package com.titut.inventory.ui.friends

import androidx.lifecycle.ViewModel
import com.titut.inventory.repository.FriendRepository
import javax.inject.Inject

class FriendsViewModel @Inject constructor(
    private var friendRepository: FriendRepository
) : ViewModel() {
    fun getFriends() = friendRepository.getFriends()

    fun getFriendsWithTools() = friendRepository.getFriendsWithTools()
}