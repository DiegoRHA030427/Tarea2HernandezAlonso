package com.escom.android_views.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.escom.android_views.R
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.slider.Slider
import java.util.Calendar

// Sección 3: Ajustes del Juego. Checkboxes de clima, RadioButtons de cuarto,
// un Switch, un Slider para la línea de yardaje y un selector de fecha.
class AjustesJuegoFragment : Fragment() {

    override fun onCreateView(
        inflador: LayoutInflater,
        contenedor: ViewGroup?,
        estadoGuardado: Bundle?
    ): View {
        val vista = inflador.inflate(R.layout.fragment_ajustes_juego, contenedor, false)

        val textoLineaYardaje = vista.findViewById<TextView>(R.id.texto_linea_yardaje)
        val slider = vista.findViewById<Slider>(R.id.slider_linea_yardaje)
        slider.addOnChangeListener { _, valor, _ ->
            textoLineaYardaje.text = getString(R.string.formato_linea_yardaje, valor.toInt())
        }

        val textoDiaPartido = vista.findViewById<TextView>(R.id.texto_dia_partido)
        vista.findViewById<View>(R.id.boton_elegir_fecha).setOnClickListener {
            val hoy = Calendar.getInstance()
            DatePickerDialog(
                requireContext(),
                { _, anio, mes, dia ->
                    textoDiaPartido.text = getString(R.string.formato_dia_partido, dia, mes + 1, anio)
                },
                hoy.get(Calendar.YEAR),
                hoy.get(Calendar.MONTH),
                hoy.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        vista.findViewById<View>(R.id.boton_aplicar_ajustes).setOnClickListener {
            aplicarAjustes(vista)
        }

        return vista
    }

    private fun aplicarAjustes(vista: View) {
        val climaSeleccionado = mutableListOf<String>()
        if (vista.findViewById<CheckBox>(R.id.check_soleado).isChecked) {
            climaSeleccionado.add(getString(R.string.clima_soleado))
        }
        if (vista.findViewById<CheckBox>(R.id.check_lluvia).isChecked) {
            climaSeleccionado.add(getString(R.string.clima_lluvia))
        }
        if (vista.findViewById<CheckBox>(R.id.check_nieve).isChecked) {
            climaSeleccionado.add(getString(R.string.clima_nieve))
        }

        val grupoCuartos = vista.findViewById<RadioGroup>(R.id.grupo_cuartos)
        val cuartoSeleccionado = when (grupoCuartos.checkedRadioButtonId) {
            R.id.radio_cuarto_2 -> getString(R.string.cuarto_2)
            R.id.radio_cuarto_3 -> getString(R.string.cuarto_3)
            R.id.radio_cuarto_4 -> getString(R.string.cuarto_4)
            else -> getString(R.string.cuarto_1)
        }

        val transmisionActiva = vista.findViewById<MaterialSwitch>(R.id.switch_transmision).isChecked

        // El resumen se arma en variables simples y se muestra como confirmación.
        val resumen = buildString {
            append(climaSeleccionado.joinToString(", ").ifEmpty { "sin clima" })
            append(" · ")
            append(cuartoSeleccionado)
            append(" · ")
            append(if (transmisionActiva) "En vivo" else "Sin transmisión")
        }

        Toast.makeText(
            requireContext(),
            "${getString(R.string.mensaje_ajustes_aplicados)}: $resumen",
            Toast.LENGTH_LONG
        ).show()
    }
}
