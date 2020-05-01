package com.titut.inventory.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.titut.inventory.db.entity.Tool

@Dao
interface ToolDao {
    @Insert
    fun insert(tool: Tool)

    @Insert
    fun insertAll(tools: List<Tool>)

    @Query("SELECT * FROM tools_table")
    fun getAllTools(): LiveData<List<Tool>>
}