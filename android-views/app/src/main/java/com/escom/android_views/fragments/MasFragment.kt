package com.escom.android_views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.escom.android_views.MainActivity
import com.escom.android_views.R

// Pestaña "Más": como el menú inferior solo admite 5 elementos, aquí se agrupan
// el acceso a "Resumen del Partido" y "Formaciones" mediante dos botones simples.
class MasFragment : Fragment() {
    override fun onCreateView(
        inflador: LayoutInflater,
        contenedor: ViewGroup?,
        estadoGuardado: Bundle?
    ): View {
        return inflador.inflate(R.layout.fragment_mas, contenedor, false)
    }

    override fun onViewCreated(vista: View, estadoGuardado: Bundle?) {
        super.onViewCreated(vista, estadoGuardado)

        val actividadPrincipal = activity as? MainActivity ?: return

        vista.findViewById<View>(R.id.boton_resumen_partido).setOnClickListener {
            actividadPrincipal.mostrarFragmento(ResumenPartidoFragment())
        }
        vista.findViewById<View>(R.id.boton_formaciones).setOnClickListener {
            actividadPrincipal.mostrarFragmento(FormacionesFragment())
        }
    }
}
