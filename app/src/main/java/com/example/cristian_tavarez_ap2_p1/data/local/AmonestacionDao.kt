package com.example.cristian_tavarez_ap2_p1.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.cristian_tavarez_ap2_p1.domain.model.AmonestacionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AmonestacionDao {
    @Upsert
    suspend fun upsert(amonestacion: AmonestacionEntity)

    @Delete
    suspend fun delete(amonestacion: AmonestacionEntity)

    @Query("SELECT * FROM Amonestaciones")
    fun getAll(): Flow<List<AmonestacionEntity>>
}