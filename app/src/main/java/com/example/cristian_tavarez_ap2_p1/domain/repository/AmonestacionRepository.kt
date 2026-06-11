package com.example.cristian_tavarez_ap2_p1.domain.repository

import com.example.cristian_tavarez_ap2_p1.domain.model.AmonestacionEntity
import kotlinx.coroutines.flow.Flow

interface AmonestacionRepository {
    fun getAll(): Flow<List<AmonestacionEntity>>
    suspend fun save(amonestacion: AmonestacionEntity)
    suspend fun delete(amonestacion: AmonestacionEntity)
}