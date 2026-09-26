package com.escom.android_compose

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.escom.android_compose.ui.theme.AmarilloPenalti
import com.escom.android_compose.ui.theme.AzulMarinoEquipo
import com.escom.android_compose.ui.theme.BlancoLineas
import com.escom.android_compose.ui.theme.DoradoEquipo
import com.escom.android_compose.ui.theme.NegroArbitro
import com.escom.android_compose.ui.theme.VerdeCampo

// Sección 5: Resumen del Partido. Tarjeta tipo boleto, barra de progreso como
// reloj de juego, Toast para el tiempo fuera y AlertDialog para el reto de jugada.
@Composable
fun PantallaResumenPartido() {
    val contexto = LocalContext.current
    var progresoReloj by remember { mutableStateOf(0.25f) }
    var mostrarDialogoReto by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Resumen del Partido",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Card(
            colors = CardDefaults.cardColors(containerColor = AzulMarinoEquipo),
            border = BorderStroke(2.dp, DoradoEquipo),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("BOLETO OFICIAL DE PARTIDO", color = DoradoEquipo)
                Text(
                    "Halcones Dorados vs Lobos Azules",
                    color = BlancoLineas,
                    modifier = Modifier.padding(top = 12.dp)
                )
                Text(
                    "Marcador: 21 - 17",
                    color = BlancoLineas,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }

        Text(text = "Reloj de juego", style = MaterialTheme.typography.titleMedium, color = VerdeCampo)
        LinearProgressIndicator(
            progress = { progresoReloj },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 16.dp)
        )

        Button(
            onClick = { progresoReloj = (progresoReloj + 0.15f).coerceAtMost(1f) },
            colors = ButtonDefaults.buttonColors(containerColor = VerdeCampo),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        ) {
            Text("Avanzar Reloj")
        }

        OutlinedButton(
            onClick = {
                Toast.makeText(contexto, "⏱️ Tiempo fuera solicitado por el equipo", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            Text("Tiempo Fuera")
        }

        Button(
            onClick = { mostrarDialogoReto = true },
            colors = ButtonDefaults.buttonColors(containerColor = AmarilloPenalti, contentColor = NegroArbitro),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Retar Jugada (Challenge)")
        }
    }

    if (mostrarDialogoReto) {
        AlertDialog(
            onDismissRequest = { mostrarDialogoReto = false },
            title = { Text("Bandera de Reto") },
            text = { Text("¿El entrenador confirma el reto de la última jugada?") },
            confirmButton = {
                TextButton(onClick = {
                    mostrarDialogoReto = false
                    Toast.makeText(contexto, "🚩 Reto confirmado: se revisa la jugada", Toast.LENGTH_SHORT).show()
                }) {
                    Text("Confirmar")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    mostrarDialogoReto = false
                    Toast.makeText(contexto, "Reto cancelado por el entrenador", Toast.LENGTH_SHORT).show()
                }) {
                    Text("Cancelar")
                }
            }
        )
    }
}
