package com.titut.inventory.db.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class ToolsWithFriends(
    @Embedded
    val tool: Tool,
    @Relation(
        parentColumn = "toolId",
        entityColumn = "friendId",
        associateBy = Junction(ToolFriendCrossRef::class)
    )
    val friends: List<Friend>
)
