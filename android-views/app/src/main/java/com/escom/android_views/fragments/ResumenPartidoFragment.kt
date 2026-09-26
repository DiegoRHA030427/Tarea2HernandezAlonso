package com.escom.android_views.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.escom.android_views.R

// Sección 5: Resumen del Partido. Tarjeta tipo boleto, ProgressBar como reloj de
// juego, Toast para el tiempo fuera y AlertDialog para el reto de jugada.
class ResumenPartidoFragment : Fragment() {

    override fun onCreateView(
        inflador: LayoutInflater,
        contenedor: ViewGroup?,
        estadoGuardado: Bundle?
    ): View {
        val vista = inflador.inflate(R.layout.fragment_resumen_partido, contenedor, false)

        val relojJuego = vista.findViewById<ProgressBar>(R.id.barra_reloj_juego)
        vista.findViewById<View>(R.id.boton_avanzar_reloj).setOnClickListener {
            relojJuego.progress = (relojJuego.progress + 15).coerceAtMost(100)
        }

        vista.findViewById<View>(R.id.boton_tiempo_fuera).setOnClickListener {
            Toast.makeText(requireContext(), R.string.mensaje_tiempo_fuera, Toast.LENGTH_SHORT).show()
        }

        vista.findViewById<View>(R.id.boton_retar_jugada).setOnClickListener {
            mostrarDialogoReto()
        }

        return vista
    }

    private fun mostrarDialogoReto() {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.dialogo_reto_titulo)
            .setMessage(R.string.dialogo_reto_mensaje)
            .setPositiveButton(R.string.dialogo_reto_confirmar) { _, _ ->
                Toast.makeText(requireContext(), R.string.mensaje_reto_confirmado, Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton(R.string.dialogo_reto_cancelar) { _, _ ->
                Toast.makeText(requireContext(), R.string.mensaje_reto_cancelado, Toast.LENGTH_SHORT).show()
            }
            .show()
    }
}
