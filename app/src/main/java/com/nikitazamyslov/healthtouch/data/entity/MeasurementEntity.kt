package com.nikitazamyslov.healthtouch.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MeasurementEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "number")
    val number: Int,
    @ColumnInfo(name = "bpm")
    val bpm: Int,
    @ColumnInfo(name = "hrv")
    val hrv: Int,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "status")
    val status: String,
)