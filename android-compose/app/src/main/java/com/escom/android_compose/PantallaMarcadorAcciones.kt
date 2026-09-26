package com.escom.android_compose

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.escom.android_compose.ui.theme.AmarilloPenalti
import com.escom.android_compose.ui.theme.NegroArbitro
import com.escom.android_compose.ui.theme.VerdeCampo

// Sección 2: Marcador y Acciones. Varios tipos de botones (relleno, contorno,
// texto y flotante) que simulan las anotaciones y decisiones de una pizarra.
@Composable
fun PantallaMarcadorAcciones() {
    val contexto = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Marcador y Acciones",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = { Toast.makeText(contexto, "¡TOUCHDOWN! Se anotan 6 puntos", Toast.LENGTH_SHORT).show() },
            colors = ButtonDefaults.buttonColors(containerColor = VerdeCampo),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        ) {
            Text("Touchdown (+6)")
        }

        OutlinedButton(
            onClick = { Toast.makeText(contexto, "Field Goal bueno: +3 puntos", Toast.LENGTH_SHORT).show() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        ) {
            Text("Field Goal (+3)")
        }

        TextButton(
            onClick = { Toast.makeText(contexto, "Punto extra anotado: +1 punto", Toast.LENGTH_SHORT).show() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        ) {
            Text("Punto Extra (+1)")
        }

        FloatingActionButton(
            onClick = {
                Toast.makeText(contexto, "🟨 Silbato del árbitro: jugada detenida", Toast.LENGTH_SHORT).show()
            },
            containerColor = AmarilloPenalti,
            contentColor = NegroArbitro
        ) {
            Text("🔔")
        }
    }
}
