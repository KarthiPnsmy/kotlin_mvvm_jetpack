package com.titut.inventory.repository

import com.titut.inventory.db.dao.FriendDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FriendRepository @Inject constructor(private val friendDao: FriendDao) {

    fun getFriends() = friendDao.getAllFriends()

    fun getFriendsWithTools() = friendDao.getFriendsWithTools()
}
