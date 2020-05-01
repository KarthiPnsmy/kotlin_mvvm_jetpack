package com.titut.inventory.db.entity

import androidx.room.Entity

@Entity(primaryKeys = ["toolId", "friendId"])
data class ToolFriendCrossRef(
    val toolId: Long,
    val friendId: Long
)