package com.example.cristian_tavarez_ap2_p1.presentation.borrame

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AmonestacionListScreen(
    viewModel: AmonestacionViewModel,
    onNavigateToForm: () -> Unit
) {
    val lista by viewModel.amonestacionesFiltradas.collectAsState()

    val conteo = lista.size
    val sumaTotal = lista.sumOf { it.monto }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.limpiarFormulario(); onNavigateToForm() }) {
                Text("+")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp)) {
            Text("Consulta de Amonestaciones", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = viewModel.filtroNombres,
                onValueChange = { viewModel.actualizarFiltros(it, viewModel.filtroRazon) },
                label = { Text("Filtrar por Empleado") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = viewModel.filtroRazon,
                onValueChange = { viewModel.actualizarFiltros(viewModel.filtroNombres, it) },
                label = { Text("Filtrar por Razón") },
                modifier = Modifier.fillMaxWidth()
            )

            LazyColumn(modifier = Modifier.weight(1f).padding(top = 16.dp)) {
                items(lista) { item ->
                    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                        Row(
                            modifier = Modifier.padding(16.dp).fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(item.nombres, style = MaterialTheme.typography.titleMedium)
                                Text("Motivo: ${item.razon}")
                                Text("Penalidad: $${item.monto}", color = Color.Red)
                            }
                            Row {
                                IconButton(onClick = {
                                    viewModel.prepararParaEditar(item)
                                    onNavigateToForm()
                                }) {
                                    Icon(Icons.Default.Edit, "Edit")
                                }

                                IconButton(onClick = {
                                    viewModel.eliminar(item)
                                }) {
                                    Icon(Icons.Default.Delete, "Delete", tint = Color.Red)
                                }
                            }
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(16.dp)
            ) {
                Text("Total Amonestaciones: $conteo")
                Text("Suma Total Penalidades: $${String.format("%.2f", sumaTotal)}", style = MaterialTheme.typography.titleLarge)
            }
        }
    }
}