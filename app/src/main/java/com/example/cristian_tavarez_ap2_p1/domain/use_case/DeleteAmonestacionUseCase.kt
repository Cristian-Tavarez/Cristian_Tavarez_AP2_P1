package com.example.cristian_tavarez_ap2_p1.domain.use_case

import com.example.cristian_tavarez_ap2_p1.domain.model.AmonestacionEntity
import com.example.cristian_tavarez_ap2_p1.domain.repository.AmonestacionRepository
import javax.inject.Inject

class DeleteAmonestacionUseCase @Inject constructor(
    private val repository: AmonestacionRepository
) {
    suspend operator fun invoke(amonestacion: AmonestacionEntity) {
        repository.delete(amonestacion)
    }
}