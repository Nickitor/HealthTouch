package com.nikitazamyslov.healthtouch.data.dao

import androidx.room.*
import com.nikitazamyslov.healthtouch.data.entity.MeasurementEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MeasurementDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: MeasurementEntity)

    @Update
    suspend fun update(item: MeasurementEntity)

    @Delete
    suspend fun delete(item: MeasurementEntity)

    @Query("DELETE FROM MeasurementEntity where id LIKE :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT * from MeasurementEntity WHERE id = :id")
    fun getItem(id: Int): MeasurementEntity

    @Query("SELECT * from MeasurementEntity ORDER BY id DESC")
    fun getItems(): Flow<List<MeasurementEntity>>

    @Query("SELECT * FROM MeasurementEntity WHERE number = (SELECT MAX(number) FROM MeasurementEntity)")
    suspend fun getLastItem(): List<MeasurementEntity>
}