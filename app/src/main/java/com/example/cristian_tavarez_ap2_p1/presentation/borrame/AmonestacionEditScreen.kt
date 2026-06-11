package com.example.cristian_tavarez_ap2_p1.presentation.borrame

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun AmonestacionFormScreen(viewModel: AmonestacionViewModel, onNavigateBack: () -> Unit) {
    val context = LocalContext.current
    var errorNombres by remember { mutableStateOf(false) }
    var errorRazon by remember { mutableStateOf(false) }
    var errorMonto by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(if (viewModel.amonestacionSeleccionada == null) "Nueva Amonestación" else "Editar Amonestación", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = viewModel.nombresInput,
            onValueChange = { viewModel.nombresInput = it; errorNombres = false },
            label = { Text("Nombre del Empleado") },
            modifier = Modifier.fillMaxWidth(),
            isError = errorNombres,
            supportingText = { if (errorNombres) Text("Campo obligatorio", color = MaterialTheme.colorScheme.error) }
        )

        OutlinedTextField(
            value = viewModel.razonInput,
            onValueChange = { viewModel.razonInput = it; errorRazon = false },
            label = { Text("Razón / Motivo") },
            modifier = Modifier.fillMaxWidth(),
            isError = errorRazon,
            supportingText = { if (errorRazon) Text("Campo obligatorio", color = MaterialTheme.colorScheme.error) }
        )

        OutlinedTextField(
            value = viewModel.montoInput,
            onValueChange = { viewModel.montoInput = it; errorMonto = false },
            label = { Text("Monto Penalización") },
            modifier = Modifier.fillMaxWidth(),
            isError = errorMonto,
            supportingText = { if (errorMonto) Text("Monto inválido", color = MaterialTheme.colorScheme.error) }
        )

        Button(
            onClick = {
                errorNombres = viewModel.nombresInput.isBlank()
                errorRazon = viewModel.razonInput.isBlank()
                errorMonto = (viewModel.montoInput.toDoubleOrNull() ?: 0.0) <= 0.0

                if (!errorNombres && !errorRazon && !errorMonto) {
                    if (viewModel.guardar()) {
                        Toast.makeText(context, "Guardado exitoso", Toast.LENGTH_SHORT).show()
                        onNavigateBack()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) { Text("Guardar Registro") }

        TextButton(onClick = onNavigateBack, modifier = Modifier.fillMaxWidth()) { Text("Cancelar") }
    }
}