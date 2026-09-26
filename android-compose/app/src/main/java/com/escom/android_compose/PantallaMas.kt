package com.escom.android_compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Pestaña "Más": agrupa el acceso a "Resumen del Partido" y "Formaciones",
// ya que el menú inferior solo admite 5 elementos.
@Composable
fun PantallaMas(irAResumen: () -> Unit, irAFormaciones: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = irAResumen, modifier = Modifier.fillMaxWidth()) {
            Text("Resumen del Partido")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = irAFormaciones, modifier = Modifier.fillMaxWidth()) {
            Text("Formaciones")
        }
    }
}
