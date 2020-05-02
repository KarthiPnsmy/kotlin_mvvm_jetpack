package com.titut.inventory

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.titut.inventory.db.InventoryDatabase
import com.titut.inventory.db.dao.ToolDao
import com.titut.inventory.db.entity.Tool
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
class ToolsDaoTest {

    @Rule
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var inventoryDatabase: InventoryDatabase
    private lateinit var toolDao: ToolDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        inventoryDatabase = Room
            .inMemoryDatabaseBuilder(context, InventoryDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        toolDao = inventoryDatabase.toolDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        inventoryDatabase.close()
    }

    @Test
    fun insertTool() = runBlocking {
        toolDao.insert(TestUtils.TOOL1)
        val tools: List<Tool> = LiveDataTestUtil.getValue(toolDao.getAllTools())

        assertTrue(tools.isNotEmpty())
    }

    @Test
    fun verifyInsert() = runBlocking {
        toolDao.insert(TestUtils.TOOL1)
        val tools: List<Tool> = LiveDataTestUtil.getValue(toolDao.getAllTools())

        assertEquals(tools[0].name, TestUtils.TOOL1.name)
    }

    @Test
    fun insertMultipleTools() = runBlocking {
        toolDao.insertAll(TestUtils.TOOLS)
        val tools: List<Tool> = LiveDataTestUtil.getValue(toolDao.getAllTools())

        assertEquals(tools.size, TestUtils.TOOLS.size)
    }

    @Test
    fun saveToolWithFriend() = runBlocking {
        toolDao.insert(TestUtils.TOOL1)
        toolDao.insertToolWithFriend(TestUtils.TOOL_FRIEND_CROSS_REF1)
        val tools: List<Tool> = LiveDataTestUtil.getValue(toolDao.getAllTools())

        assertTrue(tools.isNotEmpty())
    }
}
