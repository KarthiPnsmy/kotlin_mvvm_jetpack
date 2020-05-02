package com.titut.inventory.ui.tools

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.titut.inventory.db.entity.ToolFriendCrossRef
import com.titut.inventory.repository.ToolRepository

class ToolsViewModel(application: Application) : AndroidViewModel(application) {

    private var toolsRepository: ToolRepository = ToolRepository(application)

    fun getTools() = toolsRepository.getTools()

    fun getToolsWithFriends() = toolsRepository.getToolsWithFriends()

    fun saveToolWithFriend(toolWithFriend: ToolFriendCrossRef) = toolsRepository.saveToolWithFriend(toolWithFriend)

    fun deleteToolWithFriend(toolWithFriend: ToolFriendCrossRef) = toolsRepository.deleteToolWithFriend(toolWithFriend)
}