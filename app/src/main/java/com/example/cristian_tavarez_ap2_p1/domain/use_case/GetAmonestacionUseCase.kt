package com.example.cristian_tavarez_ap2_p1.domain.use_case

import com.example.cristian_tavarez_ap2_p1.domain.model.AmonestacionEntity
import com.example.cristian_tavarez_ap2_p1.domain.repository.AmonestacionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAmonestacionUseCase @Inject constructor(
    private val repository: AmonestacionRepository
) {
    operator fun invoke(): Flow<List<AmonestacionEntity>> {
        return repository.getAll()
    }
}