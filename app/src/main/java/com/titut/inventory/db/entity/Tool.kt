package com.titut.inventory.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tools_table")
data class Tool(
    var name: String,
    var image: Int,
    var quantity: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}