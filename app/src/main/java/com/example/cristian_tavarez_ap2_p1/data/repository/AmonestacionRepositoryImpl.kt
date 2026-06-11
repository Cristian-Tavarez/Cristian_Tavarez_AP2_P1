package com.example.cristian_tavarez_ap2_p1.data.repository

import com.example.cristian_tavarez_ap2_p1.data.local.AmonestacionDao
import com.example.cristian_tavarez_ap2_p1.domain.model.AmonestacionEntity
import com.example.cristian_tavarez_ap2_p1.domain.repository.AmonestacionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AmonestacionRepositoryImpl @Inject constructor(
    private val dao: AmonestacionDao
) : AmonestacionRepository {
    override fun getAll(): Flow<List<AmonestacionEntity>> = dao.getAll()
    override suspend fun save(amonestacion: AmonestacionEntity) = dao.upsert(amonestacion)
    override suspend fun delete(amonestacion: AmonestacionEntity) = dao.delete(amonestacion)
}