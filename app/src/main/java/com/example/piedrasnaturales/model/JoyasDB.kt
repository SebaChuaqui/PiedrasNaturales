package com.example.piedrasnaturales.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

private const val DATA_BASE_NAME="joyas_db"

@Database(entities = [PiedrasItem::class], version = 1)

abstract class JoyasDB : RoomDatabase() {

    abstract fun getClimaDAO(): JoyasDAO

    companion object {

        @Volatile
        private var INSTANCE: JoyasDB? = null

        fun getDataBase(context: Context): JoyasDB {

            val tempInterface = INSTANCE
            if (tempInterface != null) {
                return tempInterface
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    JoyasDB::class.java,
                    DATA_BASE_NAME
                )
                    .build()
                INSTANCE = instance
                return instance

            }
        }
    }
}