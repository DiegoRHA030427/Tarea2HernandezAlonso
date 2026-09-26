package com.escom.android_views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.escom.android_views.R

// Sección 2: Marcador y Acciones. Varios tipos de botones (relleno, contorno,
// texto y flotante) que simulan las anotaciones y decisiones de una pizarra.
class MarcadorAccionesFragment : Fragment() {

    override fun onCreateView(
        inflador: LayoutInflater,
        contenedor: ViewGroup?,
        estadoGuardado: Bundle?
    ): View {
        val vista = inflador.inflate(R.layout.fragment_marcador_acciones, contenedor, false)

        vista.findViewById<View>(R.id.boton_touchdown).setOnClickListener {
            mostrarMensaje(R.string.mensaje_touchdown)
        }
        vista.findViewById<View>(R.id.boton_field_goal).setOnClickListener {
            mostrarMensaje(R.string.mensaje_field_goal)
        }
        vista.findViewById<View>(R.id.boton_punto_extra).setOnClickListener {
            mostrarMensaje(R.string.mensaje_punto_extra)
        }
        vista.findViewById<View>(R.id.boton_silbato).setOnClickListener {
            mostrarMensaje(R.string.mensaje_silbato)
        }

        return vista
    }

    private fun mostrarMensaje(idMensaje: Int) {
        Toast.makeText(requireContext(), idMensaje, Toast.LENGTH_SHORT).show()
    }
}
