package com.nikitazamyslov.healthtouch.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nikitazamyslov.healthtouch.data.dao.MeasurementDao
import com.nikitazamyslov.healthtouch.data.entity.MeasurementEntity

@Database(entities = [MeasurementEntity::class], version = 1, exportSchema = false)
abstract class MeasurementRoomDatabase : RoomDatabase() {

    abstract fun itemDao(): MeasurementDao

    companion object {
        @Volatile
        private var INSTANCE: MeasurementRoomDatabase? = null
        fun getDatabase(context: Context): MeasurementRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MeasurementRoomDatabase::class.java,
                    "item_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
