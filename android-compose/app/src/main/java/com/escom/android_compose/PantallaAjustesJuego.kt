package com.escom.android_compose

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.escom.android_compose.ui.theme.AzulMarinoEquipo
import com.escom.android_compose.ui.theme.VerdeCampo
import kotlinx.coroutines.launch
import java.util.Calendar

// Sección 3: Ajustes del Juego. Checkboxes de clima, RadioButtons de cuarto,
// un Switch, un Slider para la línea de yardaje y un selector de fecha.
// La confirmación final usa el SnackbarHost del Scaffold principal, como
// variante de Toast dentro del mismo catálogo.
@Composable
fun PantallaAjustesJuego(estadoSnackbar: SnackbarHostState) {
    val contexto = LocalContext.current
    val alcanceCorutina = rememberCoroutineScope()
    val calendario = remember { Calendar.getInstance() }

    var climaSoleado by remember { mutableStateOf(false) }
    var climaLluvia by remember { mutableStateOf(false) }
    var climaNieve by remember { mutableStateOf(false) }

    val cuartos = listOf("1er Cuarto", "2do Cuarto", "3er Cuarto", "4to Cuarto")
    var cuartoSeleccionado by remember { mutableStateOf(cuartos.first()) }

    var transmisionEnVivo by remember { mutableStateOf(false) }

    var lineaYardaje by remember { mutableStateOf(50f) }

    var diaPartido by remember { mutableStateOf("Sin seleccionar") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Ajustes del Juego",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(text = "Clima del partido", style = MaterialTheme.typography.titleMedium, color = VerdeCampo)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = climaSoleado, onCheckedChange = { climaSoleado = it })
            Text("Soleado")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = climaLluvia, onCheckedChange = { climaLluvia = it })
            Text("Lluvia")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = climaNieve, onCheckedChange = { climaNieve = it })
            Text("Nieve")
        }

        Text(
            text = "Cuarto actual",
            style = MaterialTheme.typography.titleMedium,
            color = VerdeCampo,
            modifier = Modifier.padding(top = 16.dp)
        )
        Column(Modifier.selectableGroup()) {
            cuartos.forEach { cuarto ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.selectable(
                        selected = cuarto == cuartoSeleccionado,
                        onClick = { cuartoSeleccionado = cuarto }
                    )
                ) {
                    RadioButton(selected = cuarto == cuartoSeleccionado, onClick = { cuartoSeleccionado = cuarto })
                    Text(cuarto)
                }
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
        ) {
            Text("Transmisión en vivo", modifier = Modifier.padding(end = 8.dp))
            Switch(checked = transmisionEnVivo, onCheckedChange = { transmisionEnVivo = it })
        }

        Text(
            text = "Línea de yardaje: ${lineaYardaje.toInt()} yardas",
            style = MaterialTheme.typography.titleMedium,
            color = VerdeCampo
        )
        Slider(
            value = lineaYardaje,
            onValueChange = { lineaYardaje = it },
            valueRange = 0f..100f,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Text(text = "Día del partido", style = MaterialTheme.typography.titleMedium, color = VerdeCampo)
        Text(text = diaPartido, modifier = Modifier.padding(bottom = 8.dp))
        Button(
            onClick = {
                DatePickerDialog(
                    contexto,
                    { _, anio, mes, dia -> diaPartido = "Día del partido: %02d/%02d/%d".format(dia, mes + 1, anio) },
                    calendario.get(Calendar.YEAR),
                    calendario.get(Calendar.MONTH),
                    calendario.get(Calendar.DAY_OF_MONTH)
                ).show()
            },
            modifier = Modifier.padding(bottom = 24.dp)
        ) {
            Text("Elegir fecha")
        }

        Button(
            onClick = {
                val clima = listOfNotNull(
                    "Soleado".takeIf { climaSoleado },
                    "Lluvia".takeIf { climaLluvia },
                    "Nieve".takeIf { climaNieve }
                ).joinToString(", ").ifEmpty { "sin clima" }
                val transmision = if (transmisionEnVivo) "En vivo" else "Sin transmisión"
                alcanceCorutina.launch {
                    estadoSnackbar.showSnackbar(
                        "Ajustes del juego aplicados: $clima · $cuartoSeleccionado · $transmision"
                    )
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = AzulMarinoEquipo),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Aplicar Ajustes")
        }
    }
}
