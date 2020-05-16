package com.titut.inventory.ui.tools

import androidx.lifecycle.ViewModel
import com.titut.inventory.db.entity.ToolFriendCrossRef
import com.titut.inventory.repository.ToolRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class ToolsViewModel @Inject constructor(
    private var toolsRepository: ToolRepository,
    private var coroutineScope: CoroutineScope
) : ViewModel() {
    fun getToolsWithFriends() = toolsRepository.getToolsWithFriends()

    fun getToolsWithFriendOnLoan(id: Long) = toolsRepository.getToolsWithFriendOnLoan(id)

    fun saveToolWithFriend(toolWithFriend: ToolFriendCrossRef) = coroutineScope.launch {
        toolsRepository.saveToolWithFriend(toolWithFriend)
    }

    fun deleteToolFromFriend(friendId: Long, toolId: Long) = coroutineScope.launch {
        toolsRepository.deleteToolFromFriend(friendId, toolId)
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}