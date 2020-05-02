package com.titut.inventory.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.titut.inventory.db.entity.Tool
import com.titut.inventory.db.entity.ToolFriendCrossRef
import com.titut.inventory.db.entity.ToolsOnLoan
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

    @Query("DELETE FROM ToolFriendCrossRef WHERE id =(SELECT id FROM ToolFriendCrossRef WHERE friendId = :friendId and toolId = :toolId LIMIT 1)")
    fun deleteToolFromFriend(friendId: Long, toolId: Long)

    @Query("SELECT Friend.name as friendName, ToolFriendCrossRef.toolId, Tool.name as toolName, Tool.image as toolImage, COUNT(ToolFriendCrossRef.toolId) as quantity\n" +
            "FROM ((ToolFriendCrossRef\n" +
            "INNER JOIN Friend ON ToolFriendCrossRef.friendId = Friend.friendId)\n" +
            "INNER JOIN Tool ON ToolFriendCrossRef.toolId = Tool.toolId)\n" +
            "WHERE Friend.friendId = :friendId\n" +
            "GROUP BY ToolFriendCrossRef.toolId")
    fun getAllToolsOnLoan(friendId: Long): LiveData<List<ToolsOnLoan>>
}
