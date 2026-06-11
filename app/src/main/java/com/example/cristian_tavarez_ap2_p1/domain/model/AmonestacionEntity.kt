package com.example.cristian_tavarez_ap2_p1.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Amonestaciones")
data class AmonestacionEntity(
    @PrimaryKey(autoGenerate = true)
    val Amonestacionid: Int = 0,
    val nombres: String,
    val razon: String,
    val monto: Double
)


