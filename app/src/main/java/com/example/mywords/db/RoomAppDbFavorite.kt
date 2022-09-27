package com.example.mywords.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [WordyEntity::class], version = 4, exportSchema = false)
abstract class WordyAppDb: RoomDatabase() {

    abstract fun wordyDAO(): WordyDAO?

    companion object {
        private var INSTANCE: WordyAppDb?= null

        val migration_1_2: Migration = object: Migration(1, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE userinfo ADD COLUMN phone TEXT DEFAULT ''")
            }
        }

        fun getAppDatabase(context: Context): WordyAppDb? {

            if(INSTANCE == null ) {

                INSTANCE = Room.databaseBuilder(
                    context.applicationContext, WordyAppDb::class.java, "AppDBB"
                )
                    .addMigrations(migration_1_2)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()

            }
            return INSTANCE
        }
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}