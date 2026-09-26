package com.escom.android_views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.escom.android_views.DatosGlobales
import com.escom.android_views.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

// Sección 1: Draft y Contratos. Valida el nombre y el número de camiseta
// (marcándolos con el "pañuelo amarillo" si están mal) y, si todo es correcto,
// guarda al jugador en la lista global DatosGlobales para que la Sección 4 lo muestre.
class DraftContratosFragment : Fragment() {

    override fun onCreateView(
        inflador: LayoutInflater,
        contenedor: ViewGroup?,
        estadoGuardado: Bundle?
    ): View {
        val vista = inflador.inflate(R.layout.fragment_draft_contratos, contenedor, false)

        val campoNombre = vista.findViewById<TextInputLayout>(R.id.campo_nombre_jugador)
        val campoNumero = vista.findViewById<TextInputLayout>(R.id.campo_numero_jugador)
        val campoContrasena = vista.findViewById<TextInputLayout>(R.id.campo_contrasena)
        val campoSalario = vista.findViewById<TextInputLayout>(R.id.campo_salario)
        val botonDraft = vista.findViewById<MaterialButton>(R.id.boton_draft)

        botonDraft.setOnClickListener {
            val nombre = campoNombre.editText?.text?.toString()?.trim().orEmpty()
            val numero = campoNumero.editText?.text?.toString()?.trim()?.toIntOrNull()

            campoNombre.error = if (nombre.isEmpty()) getString(R.string.error_nombre_requerido) else null
            campoNumero.error = if (numero == null || numero !in 1..99) {
                getString(R.string.error_numero_invalido)
            } else {
                null
            }

            if (nombre.isNotEmpty() && numero != null && numero in 1..99) {
                DatosGlobales.jugadoresDraft.add("#$numero $nombre")
                Toast.makeText(
                    requireContext(),
                    getString(R.string.mensaje_jugador_reclutado, nombre),
                    Toast.LENGTH_SHORT
                ).show()

                campoNombre.editText?.text?.clear()
                campoNumero.editText?.text?.clear()
                campoContrasena.editText?.text?.clear()
                campoSalario.editText?.text?.clear()
            }
        }

        return vista
    }
}
