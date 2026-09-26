package com.escom.android_compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.escom.android_compose.ui.theme.DoradoEquipo
import com.escom.android_compose.ui.theme.NegroArbitro

// Sección 4: Roster del Equipo. Lista vertical con al menos 15 posiciones fijas
// más los jugadores que se hayan reclutado en la Sección 1 (lista global).
private val rosterBase = listOf(
    "QB - Mariscal de Campo",
    "RB - Corredor",
    "WR1 - Receptor Abierto",
    "WR2 - Receptor Abierto",
    "TE - Ala Cerrada",
    "LT - Tackle Izquierdo",
    "LG - Guardia Izquierdo",
    "C - Centro",
    "RG - Guardia Derecho",
    "RT - Tackle Derecho",
    "DE - Ala Defensiva",
    "DT - Tackle Defensivo",
    "LB - Apoyador",
    "CB - Esquinero",
    "S - Profundo"
)

@Composable
fun PantallaRosterEquipo() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Roster del Equipo",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(16.dp)
        )

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(rosterBase) { posicion ->
                Text(
                    text = posicion,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                )
            }
            items(DatosGlobales.jugadoresDraft) { jugador ->
                Text(
                    text = "$jugador (Reclutado en Draft)",
                    color = NegroArbitro,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(DoradoEquipo)
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                )
            }
        }
    }
}
