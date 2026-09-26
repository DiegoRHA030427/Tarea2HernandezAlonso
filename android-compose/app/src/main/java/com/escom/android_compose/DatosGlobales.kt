package com.escom.android_compose

import androidx.compose.runtime.mutableStateListOf

// Variable global simple para pasar datos entre pantallas: la Sección 1 (Draft)
// agrega nombres aquí y la Sección 4 (Roster) los lee para mostrarlos automáticamente.
// Es una lista observable por Compose para que la UI se actualice sola al cambiar.
object DatosGlobales {
    val jugadoresDraft = mutableStateListOf<String>()
}
