package com.titut.inventory.repository

import com.titut.inventory.db.dao.ToolDao
import com.titut.inventory.db.entity.ToolFriendCrossRef


class ToolRepository(private val toolDao: ToolDao) {

    fun getToolsWithFriends() = toolDao.getToolsWithFriends()

    fun getToolsWithFriendOnLoan(friendId: Long) = toolDao.getAllToolsOnLoan(friendId)

    suspend fun saveToolWithFriend(toolWithFriend: ToolFriendCrossRef) {
        toolDao.insertToolWithFriend(toolWithFriend)
    }

    suspend fun deleteToolFromFriend(friendId: Long, toolId: Long) {
        toolDao.deleteToolFromFriend(friendId, toolId)
    }
}
