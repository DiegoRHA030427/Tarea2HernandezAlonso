package com.escom.android_views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.escom.android_views.DatosGlobales
import com.escom.android_views.R

// Sección 4: Roster del Equipo. RecyclerView con al menos 15 posiciones fijas
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

    private val adaptador = RosterAdapter()

    override fun onCreateView(
        inflador: LayoutInflater,
        contenedor: ViewGroup?,
        estadoGuardado: Bundle?
    ): View {
        val vista = inflador.inflate(R.layout.fragment_roster_equipo, contenedor, false)
        val listaRoster = vista.findViewById<RecyclerView>(R.id.lista_roster)
        listaRoster.layoutManager = LinearLayoutManager(requireContext())
        listaRoster.adapter = adaptador
        return vista
    }

    // Se refresca cada vez que la pestaña vuelve a mostrarse, así los jugadores
    // reclutados en la Sección 1 aparecen sin necesidad de volver a crear el Fragment.
    override fun onResume() {
        super.onResume()
        val jugadores = rosterBase.map { it to false } + DatosGlobales.jugadoresDraft.map { it to true }
        adaptador.actualizar(jugadores)
    }
}

// Adaptador simple: cada fila muestra el nombre del jugador y, si fue
// reclutado en el Draft, un fondo dorado distinto.
private class RosterAdapter : RecyclerView.Adapter<RosterAdapter.RosterViewHolder>() {

    private var jugadores = listOf<Pair<String, Boolean>>()

    class RosterViewHolder(val texto: TextView) : RecyclerView.ViewHolder(texto)

    fun actualizar(nuevaLista: List<Pair<String, Boolean>>) {
        jugadores = nuevaLista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(padre: ViewGroup, tipoVista: Int): RosterViewHolder {
        val texto = LayoutInflater.from(padre.context)
            .inflate(R.layout.item_roster, padre, false) as TextView
        return RosterViewHolder(texto)
    }

    override fun getItemCount(): Int = jugadores.size

    override fun onBindViewHolder(holder: RosterViewHolder, posicion: Int) {
        val (nombre, esReclutado) = jugadores[posicion]
        val contexto = holder.texto.context
        holder.texto.text = if (esReclutado) {
            "$nombre ${contexto.getString(R.string.etiqueta_reclutado)}"
        } else {
            nombre
        }
        holder.texto.setBackgroundColor(
            contexto.getColor(if (esReclutado) R.color.dorado_equipo else android.R.color.transparent)
        )
    }
}
