package com.titut.inventory.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.titut.inventory.R
import com.titut.inventory.db.InventoryDatabase.Seed.preloadFriendData
import com.titut.inventory.db.InventoryDatabase.Seed.preloadToolsData
import com.titut.inventory.db.dao.FriendDao
import com.titut.inventory.db.dao.ToolDao
import com.titut.inventory.db.entity.Friend
import com.titut.inventory.db.entity.Tool
import com.titut.inventory.db.entity.ToolFriendCrossRef
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = [Tool::class, Friend::class, ToolFriendCrossRef::class], version = 1)
abstract class InventoryDatabase : RoomDatabase() {

    abstract fun toolDao(): ToolDao
    abstract fun friendDao(): FriendDao

    companion object {
        @Volatile
        private var INSTANCE: InventoryDatabase? = null

        fun getInstance(context: Context, scope: CoroutineScope): InventoryDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context, scope).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context, scope: CoroutineScope) =
            Room.databaseBuilder(
                context.applicationContext,
                InventoryDatabase::class.java, "inventory_database"
            )
                .addCallback(InventoryDatabaseCallback(scope))
                .build()
    }

    private class InventoryDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val toolDao = database.toolDao()
                    val friendDao = database.friendDao()

                    friendDao.insertAll(preloadFriendData())
                    toolDao.insertAll(preloadToolsData())
                }
            }
        }
    }

    object Seed {
        fun preloadFriendData(): List<Friend> {
            return listOf(
                Friend("Brian", R.drawable.ic_social),
                Friend("Luke", R.drawable.ic_social),
                Friend("Letty", R.drawable.ic_social),
                Friend("Shaw", R.drawable.ic_social),
                Friend("Parker", R.drawable.ic_social)
            )
        }

        fun preloadToolsData(): List<Tool> {
            return listOf(
                Tool("Wrench", R.drawable.ic_wrench, 6),
                Tool("Cutter", R.drawable.ic_cutter, 15),
                Tool("Pliers", R.drawable.ic_pliers, 12),
                Tool("Screwdriver", R.drawable.ic_screwdriver, 13),
                Tool("Welding machine", R.drawable.ic_welding_machine, 3),
                Tool("Welding glasses", R.drawable.ic_welding_glasses, 7),
                Tool("Hammer", R.drawable.ic_hammer, 4),
                Tool("Measuring Tape", R.drawable.ic_measuring_tape, 9),
                Tool("Alan key set", R.drawable.ic_key, 4),
                Tool("Air compressor", R.drawable.ic_air_compressor, 2)
            )
        }
    }
}