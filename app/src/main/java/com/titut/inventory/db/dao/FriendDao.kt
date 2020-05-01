package com.titut.inventory.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.titut.inventory.db.entity.Friend

@Dao
interface FriendDao {
    @Insert
    fun insert(friend: Friend)

    @Insert
    fun insertAll(friends: List<Friend>)

    @Query("SELECT * FROM friends_table")
    fun getAllFriends(): LiveData<List<Friend>>
}