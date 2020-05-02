package com.titut.inventory.db.entity

data class ToolsOnLoan(
    val friendName: String,
    val toolId: Long,
    val toolName: String,
    val toolImage: Int,
    val quantity: Int
)
