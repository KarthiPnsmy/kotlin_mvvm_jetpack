package com.titut.inventory.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tool(
    var name: String,
    var image: Int,
    var quantity: Int
) {
    @PrimaryKey(autoGenerate = true)
    var toolId: Long = 0
}