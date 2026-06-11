package com.example.cristian_tavarez_ap2_p1.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cristian_tavarez_ap2_p1.data.local.AmonestacionDao
import com.example.cristian_tavarez_ap2_p1.domain.model.AmonestacionEntity

@Database(entities = [AmonestacionEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val amonestacionDao: AmonestacionDao
}