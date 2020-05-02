package com.titut.inventory.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToolFriendCrossRef(
    val toolId: Long,
    val friendId: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}