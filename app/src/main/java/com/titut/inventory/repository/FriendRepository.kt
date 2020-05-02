package com.titut.inventory.repository

import com.titut.inventory.db.dao.FriendDao

class FriendRepository(private val friendDao: FriendDao) {

    fun getFriends() = friendDao.getAllFriends()

    fun getFriendsWithTools() = friendDao.getFriendsWithTools()
}
