package com.titut.inventory.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.titut.inventory.db.entity.Friend
import com.titut.inventory.db.entity.FriendsWithTools

@Dao
interface FriendDao {
    @Insert
    fun insert(friend: Friend)

    @Insert
    fun insertAll(friends: List<Friend>)

    @Query("SELECT * FROM Friend")
    fun getAllFriends(): LiveData<List<Friend>>

    @Transaction
    @Query("SELECT * FROM Friend")
    fun getFriendsWithTools(): LiveData<List<FriendsWithTools>>
}