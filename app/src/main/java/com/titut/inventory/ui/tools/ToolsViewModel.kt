package com.titut.inventory.ui.tools

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.titut.inventory.db.InventoryDatabase
import com.titut.inventory.db.entity.ToolFriendCrossRef
import com.titut.inventory.repository.ToolRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ToolsViewModel @Inject constructor(
    application: Application,
    testString: String
) : ViewModel() {
    private var toolsRepository: ToolRepository

    init {
        val toolDao = InventoryDatabase.getInstance(application, viewModelScope).toolDao()
        toolsRepository = ToolRepository(toolDao)
        println("@@@@ ToolsViewModel $testString")
    }

    fun getToolsWithFriends() = toolsRepository.getToolsWithFriends()

    fun getToolsWithFriendOnLoan(id: Long) = toolsRepository.getToolsWithFriendOnLoan(id)

    fun saveToolWithFriend(toolWithFriend: ToolFriendCrossRef) = viewModelScope.launch {
        toolsRepository.saveToolWithFriend(toolWithFriend)
    }

    fun deleteToolFromFriend(friendId: Long, toolId: Long) = viewModelScope.launch {
        toolsRepository.deleteToolFromFriend(friendId, toolId)
    }
}