package com.example.cristian_tavarez_ap2_p1.presentation.borrame

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cristian_tavarez_ap2_p1.domain.model.AmonestacionEntity
import com.example.cristian_tavarez_ap2_p1.domain.use_case.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AmonestacionViewModel @Inject constructor(
    private val getAmonestacionesUseCase: GetAmonestacionUseCase,
    private val saveAmonestacionUseCase: SaveAmonestacionUseCase,
    private val deleteAmonestacionUseCase: DeleteAmonestacionUseCase
) : ViewModel() {

    // Filtros
    var filtroNombres by mutableStateOf("")
    var filtroRazon by mutableStateOf("")

    // Formulario
    var amonestacionSeleccionada by mutableStateOf<AmonestacionEntity?>(null)
    var nombresInput by mutableStateOf("")
    var razonInput by mutableStateOf("")
    var montoInput by mutableStateOf("")

    private val _amonestaciones = getAmonestacionesUseCase()
    private val _fNombres = MutableStateFlow("")
    private val _fRazon = MutableStateFlow("")

    val amonestacionesFiltradas = combine(_amonestaciones, _fNombres, _fRazon) { lista, nom, raz ->
        lista.filter {
            it.nombres.contains(nom, ignoreCase = true) && it.razon.contains(raz, ignoreCase = true)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun actualizarFiltros(nombres: String, razon: String) {
        filtroNombres = nombres
        filtroRazon = razon
        _fNombres.value = nombres
        _fRazon.value = razon
    }

    fun prepararParaEditar(entity: AmonestacionEntity) {
        amonestacionSeleccionada = entity
        nombresInput = entity.nombres
        razonInput = entity.razon
        montoInput = entity.monto.toString()
    }

    fun limpiarFormulario() {
        amonestacionSeleccionada = null
        nombresInput = ""
        razonInput = ""
        montoInput = ""
    }

    fun guardar(): Boolean {
        val monto = montoInput.toDoubleOrNull() ?: 0.0
        if (nombresInput.isBlank() || razonInput.isBlank() || monto <= 0.0) return false

        viewModelScope.launch {
            val entity = AmonestacionEntity(
                Amonestacionid = amonestacionSeleccionada?.Amonestacionid ?: 0,
                nombres = nombresInput,
                razon = razonInput,
                monto = monto
            )
            saveAmonestacionUseCase(entity)
            limpiarFormulario()
        }
        return true
    }

    fun eliminar(entity: AmonestacionEntity) {
        viewModelScope.launch { deleteAmonestacionUseCase(entity) }
    }
}