package com.example.accomplished.database

import android.content.Context
import android.provider.ContactsContract
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Category::class, Activity::class, Type::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun activityDao(): ActivityDao
    abstract fun typeDao(): TypeDao
    companion object {

        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(
                        context
                    )
                        .also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java, "Sample.db")
                // prepopulate the database after onCreate was called
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // insert the data on the IO Thread
                        ioThread {
                            getInstance(
                                context
                            ).categoryDao()
                                .insertAll(PREPOPULATE_DATA)
                        }
                        /*ioThread { getInstance(context).activityDao().insertAll(listOf(Activity(1,"Running","fast",1,1), Activity(2,"Gym",null,1,1))) }
                    */}
                }).allowMainThreadQueries()
                .build()

        val PREPOPULATE_DATA = listOf(
            Category(
                1,
                "Sport",
                "Track your fitness"
            ),
            Category(
                2,
                "Routine",
                "Manage your time"
            ),
            Category(3,"Health", "Good habits")
        )
    }
}