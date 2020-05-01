package com.titut.inventory.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Friend(
    var name: String,
    var image: Int
) {
    @PrimaryKey(autoGenerate = true)
    var friendId: Long = 0
}