package com.titut.inventory.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.titut.inventory.R

@Entity(tableName = "friends_table")
data class Friend(
    var name: String,
    var image: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}