package com.example.cristian_tavarez_ap2_p1.presentation.borrame

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun AmonestacionEditScreen(
    viewModel: AmonestacionViewModel,
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current
    val esEdicion = viewModel.amonestacionSeleccionada != null

    var errorNombres by remember { mutableStateOf(false) }
    var errorRazon by remember { mutableStateOf(false) }
    var errorMonto by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onNavigateBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver atrás"
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = if (!esEdicion) "Nueva Amonestación" else "Editar Amonestación",
                style = MaterialTheme.typography.headlineMedium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

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
        ) {
            Text(if (!esEdicion) "Guardar Registro" else "Aplicar Cambios")
        }

        if (esEdicion) {
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    viewModel.eliminar(viewModel.amonestacionSeleccionada!!)
                    Toast.makeText(context, "Registro eliminado", Toast.LENGTH_SHORT).show()
                    onNavigateBack()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Eliminar Amonestación", color = Color.White)
            }
        }
    }
}