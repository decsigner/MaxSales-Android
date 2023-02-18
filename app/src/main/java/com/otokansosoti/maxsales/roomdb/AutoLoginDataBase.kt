package com.otokansosoti.maxsales.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [AutoLoginTable::class], version = 1)
abstract class AutoLoginDataBase : RoomDatabase() {
    abstract fun autoLoginDao() : AutoLoginDao

    companion object {
        private var instance: AutoLoginDataBase? = null

        @Synchronized
        fun getInstance(ctx: Context): AutoLoginDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(ctx.applicationContext, AutoLoginDataBase::class.java, "autologin_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }

            return instance!!
        }
    }
}