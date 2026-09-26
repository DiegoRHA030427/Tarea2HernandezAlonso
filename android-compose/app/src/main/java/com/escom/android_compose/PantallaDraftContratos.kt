package com.escom.android_compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import android.widget.Toast
import com.escom.android_compose.ui.theme.DoradoEquipo
import com.escom.android_compose.ui.theme.NegroArbitro

// Sección 1: Draft y Contratos. Valida el nombre y el número de camiseta
// (con el "pañuelo amarillo" si están mal) y, si todo es correcto, guarda al
// jugador en la lista global DatosGlobales para que la Sección 4 lo muestre.
@Composable
fun PantallaDraftContratos() {
    val contexto = LocalContext.current

    var nombre by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var salario by remember { mutableStateOf("") }

    var errorNombre by remember { mutableStateOf<String?>(null) }
    var errorNumero by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Draft y Contratos",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre del jugador") },
            isError = errorNombre != null,
            supportingText = { errorNombre?.let { Text(it) } },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )

        OutlinedTextField(
            value = numero,
            onValueChange = { numero = it },
            label = { Text("Número de camiseta (1-99)") },
            isError = errorNumero != null,
            supportingText = { errorNumero?.let { Text(it) } },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )

        OutlinedTextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )

        OutlinedTextField(
            value = salario,
            onValueChange = { salario = it },
            label = { Text("Salario anual ($)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        )

        Button(
            onClick = {
                val numeroValido = numero.toIntOrNull()

                errorNombre = if (nombre.isBlank()) "🚩 Pañuelo amarillo: el nombre es obligatorio" else null
                errorNumero = if (numeroValido == null || numeroValido !in 1..99) {
                    "🚩 Pañuelo amarillo: número inválido (1-99)"
                } else {
                    null
                }

                if (nombre.isNotBlank() && numeroValido != null && numeroValido in 1..99) {
                    DatosGlobales.jugadoresDraft.add("#$numeroValido $nombre")
                    Toast.makeText(contexto, "$nombre fue reclutado al equipo", Toast.LENGTH_SHORT).show()
                    nombre = ""
                    numero = ""
                    contrasena = ""
                    salario = ""
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = DoradoEquipo, contentColor = NegroArbitro),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Seleccionar Jugador (Draft)")
        }
    }
}
