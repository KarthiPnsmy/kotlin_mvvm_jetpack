package com.titut.inventory

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.titut.inventory.db.InventoryDatabase
import com.titut.inventory.db.dao.FriendDao
import com.titut.inventory.db.entity.Friend
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class FriendsDaoTest {

    @Rule
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var inventoryDatabase: InventoryDatabase
    private lateinit var friendDao: FriendDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        inventoryDatabase = Room
            .inMemoryDatabaseBuilder(context, InventoryDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        friendDao = inventoryDatabase.friendDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        inventoryDatabase.close()
    }

    @Test
    fun insertFriend() = runBlocking {
        friendDao.insert(TestUtils.FRIEND1)
        val friends: List<Friend> = LiveDataTestUtil.getValue(friendDao.getAllFriends())

        assertTrue(friends.isNotEmpty())
    }

    @Test
    fun verifyInsert() = runBlocking {
        friendDao.insert(TestUtils.FRIEND1)
        val friends: List<Friend> = LiveDataTestUtil.getValue(friendDao.getAllFriends())

        assertEquals(friends[0].name, TestUtils.FRIEND1.name)
    }

    @Test
    fun insertMultipleFriends() = runBlocking {
        friendDao.insertAll(TestUtils.FRIENDS)
        val friends: List<Friend> = LiveDataTestUtil.getValue(friendDao.getAllFriends())

        assertEquals(friends.size, TestUtils.FRIENDS.size)
    }
}
