package com.titut.inventory.repository

import android.app.Application
import com.titut.inventory.db.InventoryDatabase
import com.titut.inventory.db.dao.ToolDao
import com.titut.inventory.db.entity.ToolFriendCrossRef
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext


class ToolRepository(application: Application) : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var toolDao: ToolDao?

    init {
        val db = InventoryDatabase.getDatabase(application)
        toolDao = db.toolDao()
    }

    fun getTools() = toolDao?.getAllTools()

    fun getToolsWithFriends() = toolDao?.getToolsWithFriends()

    fun getToolsWithFriendOnLoan(friendId: Long) = toolDao?.getAllToolsOnLoan(friendId)

    fun saveToolWithFriend(toolWithFriend: ToolFriendCrossRef) {
        launch  {
            saveToolWithFriendAsync(toolWithFriend)
        }
    }

    fun deleteToolFromFriend(friendId: Long, toolId: Long) {
        launch  {
            deleteToolFromFriendAsync(friendId, toolId)
        }
    }

    private suspend fun saveToolWithFriendAsync(toolWithFriend: ToolFriendCrossRef){
        withContext(Dispatchers.IO){
            toolDao?.insertToolWithFriend(toolWithFriend)
        }
    }

    private suspend fun deleteToolFromFriendAsync(friendId: Long, toolId: Long){
        withContext(Dispatchers.IO){
            toolDao?.deleteToolFromFriend(friendId, toolId)
        }
    }
}
