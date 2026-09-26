package com.escom.android_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.escom.android_compose.ui.theme.AndroidcomposeTheme

// Las 6 secciones del catálogo. El menú inferior solo admite 5 pestañas, así que
// "Resumen del Partido" y "Formaciones" se abren desde botones dentro de "Más".
enum class Pantalla {
    DRAFT_CONTRATOS,
    MARCADOR_ACCIONES,
    AJUSTES_JUEGO,
    ROSTER_EQUIPO,
    RESUMEN_PARTIDO,
    FORMACIONES,
    MAS
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidcomposeTheme {
                CatalogoNFL()
            }
        }
    }
}

@Composable
fun CatalogoNFL() {
    // Estado mutable simple que controla qué sección se muestra (sin Navigation Component).
    var pantallaActual by remember { mutableStateOf(Pantalla.DRAFT_CONTRATOS) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = pantallaActual == Pantalla.DRAFT_CONTRATOS,
                    onClick = { pantallaActual = Pantalla.DRAFT_CONTRATOS },
                    icon = { Text("🏈") },
                    label = { Text("Draft") }
                )
                NavigationBarItem(
                    selected = pantallaActual == Pantalla.MARCADOR_ACCIONES,
                    onClick = { pantallaActual = Pantalla.MARCADOR_ACCIONES },
                    icon = { Text("🚩") },
                    label = { Text("Marcador") }
                )
                NavigationBarItem(
                    selected = pantallaActual == Pantalla.AJUSTES_JUEGO,
                    onClick = { pantallaActual = Pantalla.AJUSTES_JUEGO },
                    icon = { Text("⚙️") },
                    label = { Text("Ajustes") }
                )
                NavigationBarItem(
                    selected = pantallaActual == Pantalla.ROSTER_EQUIPO,
                    onClick = { pantallaActual = Pantalla.ROSTER_EQUIPO },
                    icon = { Text("👥") },
                    label = { Text("Roster") }
                )
                NavigationBarItem(
                    selected = pantallaActual == Pantalla.MAS ||
                        pantallaActual == Pantalla.RESUMEN_PARTIDO ||
                        pantallaActual == Pantalla.FORMACIONES,
                    onClick = { pantallaActual = Pantalla.MAS },
                    icon = { Text("➕") },
                    label = { Text("Más") }
                )
            }
        }
    ) { paddingInterno ->
        Box(modifier = Modifier.padding(paddingInterno)) {
            when (pantallaActual) {
                Pantalla.DRAFT_CONTRATOS -> PantallaDraftContratos()
                Pantalla.MARCADOR_ACCIONES -> PantallaMarcadorAcciones()
                Pantalla.AJUSTES_JUEGO -> PantallaAjustesJuego()
                Pantalla.ROSTER_EQUIPO -> PantallaRosterEquipo()
                Pantalla.RESUMEN_PARTIDO -> PantallaResumenPartido()
                Pantalla.FORMACIONES -> PantallaFormaciones()
                Pantalla.MAS -> PantallaMas(
                    irAResumen = { pantallaActual = Pantalla.RESUMEN_PARTIDO },
                    irAFormaciones = { pantallaActual = Pantalla.FORMACIONES }
                )
            }
        }
    }
}
