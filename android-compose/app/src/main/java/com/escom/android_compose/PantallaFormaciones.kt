package com.escom.android_compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.escom.android_compose.ui.theme.AzulMarinoEquipo
import com.escom.android_compose.ui.theme.BlancoLineas
import com.escom.android_compose.ui.theme.DoradoEquipo
import com.escom.android_compose.ui.theme.NegroArbitro
import com.escom.android_compose.ui.theme.RojoEquipo
import com.escom.android_compose.ui.theme.VerdeCampo

// Sección 6: Formaciones. Row, Column y Box simulando posiciones en el campo,
// con TopAppBar propia ("NFL UI Catalog") y scroll general.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaFormaciones() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("NFL UI Catalog") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = VerdeCampo,
                    titleContentColor = BlancoLineas
                )
            )
        }
    ) { paddingInterno ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingInterno)
                .background(VerdeCampo)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Formaciones",
                style = MaterialTheme.typography.headlineSmall,
                color = BlancoLineas,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Formación ofensiva: I-Formation",
                color = BlancoLineas,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            // Columna que representa el campo completo.
            Column(modifier = Modifier.fillMaxWidth()) {

                // Fila de receptores.
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CasillaPosicion(texto = "WR", color = DoradoEquipo)
                    Box(modifier = Modifier.size(48.dp))
                    CasillaPosicion(texto = "TE", color = DoradoEquipo)
                }

                // Línea ofensiva.
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    listOf("LT", "LG", "C", "RG", "RT").forEach { posicion ->
                        CasillaPosicion(
                            texto = posicion,
                            color = AzulMarinoEquipo,
                            textColor = BlancoLineas,
                            tamano = 40.dp,
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )
                    }
                }

                // Mariscal de campo.
                Box(modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp), contentAlignment = Alignment.Center) {
                    CasillaPosicion(texto = "QB", color = RojoEquipo, textColor = BlancoLineas)
                }

                // Corredor.
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CasillaPosicion(texto = "RB", color = RojoEquipo, textColor = BlancoLineas)
                }
            }
        }
    }
}

@Composable
private fun CasillaPosicion(
    texto: String,
    color: Color,
    textColor: Color = NegroArbitro,
    tamano: Dp = 48.dp,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(tamano)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Text(text = texto, color = textColor)
    }
}
