package com.titut.inventory.db.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class FriendsWithTools(
    @Embedded
    val friend: Friend,
    @Relation(
        parentColumn = "friendId",
        entityColumn = "toolId",
        associateBy = Junction(ToolFriendCrossRef::class)
    )
    val tools: List<Tool>
)