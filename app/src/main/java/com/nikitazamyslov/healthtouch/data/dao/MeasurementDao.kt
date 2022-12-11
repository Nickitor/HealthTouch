package com.nikitazamyslov.healthtouch.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Dao
import com.nikitazamyslov.healthtouch.data.entity.MeasurementEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MeasurementDao {

    @Insert
    suspend fun insert(item: MeasurementEntity)

    @Update
    suspend fun update(item: MeasurementEntity)

    @Delete
    suspend fun delete(item: MeasurementEntity)

    @Query("SELECT * from MeasurementEntity WHERE number = :number")
    fun getItem(number: Int): Flow<MeasurementEntity>

    @Query("SELECT * from MeasurementEntity ORDER BY id DESC")
    fun getItems(): Flow<List<MeasurementEntity>>
}