package com.titut.inventory.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.titut.inventory.db.entity.Tool
import com.titut.inventory.db.entity.ToolFriendCrossRef
import com.titut.inventory.db.entity.ToolsWithFriends

@Dao
interface ToolDao {
    @Insert
    fun insert(tool: Tool)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToolWithFriend(toolFriendCrossRef: ToolFriendCrossRef)

    @Insert
    fun insertAll(tools: List<Tool>)

    @Query("SELECT * FROM Tool")
    fun getAllTools(): LiveData<List<Tool>>

    @Transaction
    @Query("SELECT * FROM Tool")
    fun getToolsWithFriends(): LiveData<List<ToolsWithFriends>>

    @Delete
    fun deleteToolWithFriend(toolFriendCrossRef: ToolFriendCrossRef)
}
