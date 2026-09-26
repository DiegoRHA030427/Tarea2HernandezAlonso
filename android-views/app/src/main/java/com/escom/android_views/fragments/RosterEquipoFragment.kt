package com.escom.android_views.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.escom.android_views.DatosGlobales
import com.escom.android_views.R

// Sección 4: Roster del Equipo. Lista vertical con al menos 15 posiciones fijas
// más los jugadores que se hayan reclutado en la Sección 1 (lista global).
class RosterEquipoFragment : Fragment() {

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

    private lateinit var contenedorRoster: LinearLayout

    override fun onCreateView(
        inflador: LayoutInflater,
        contenedor: ViewGroup?,
        estadoGuardado: Bundle?
    ): View {
        val vista = inflador.inflate(R.layout.fragment_roster_equipo, contenedor, false)
        contenedorRoster = vista.findViewById(R.id.contenedor_roster)
        return vista
    }

    // Se refresca cada vez que la pestaña vuelve a mostrarse, así los jugadores
    // reclutados en la Sección 1 aparecen sin necesidad de volver a crear el Fragment.
    override fun onResume() {
        super.onResume()
        actualizarListaRoster()
    }

    private fun actualizarListaRoster() {
        contenedorRoster.removeAllViews()

        rosterBase.forEach { posicion ->
            contenedorRoster.addView(crearFilaRoster(posicion, esReclutado = false))
        }
        DatosGlobales.jugadoresDraft.forEach { jugador ->
            contenedorRoster.addView(crearFilaRoster(jugador, esReclutado = true))
        }
    }

    private fun crearFilaRoster(texto: String, esReclutado: Boolean): TextView {
        return TextView(requireContext()).apply {
            this.text = if (esReclutado) "$texto ${getString(R.string.etiqueta_reclutado)}" else texto
            textSize = 16f
            setPadding(16, 24, 16, 24)
            setTextColor(resources.getColor(R.color.negro_arbitro, null))
            setBackgroundColor(
                if (esReclutado) {
                    resources.getColor(R.color.dorado_equipo, null)
                } else {
                    Color.TRANSPARENT
                }
            )
        }
    }
}
