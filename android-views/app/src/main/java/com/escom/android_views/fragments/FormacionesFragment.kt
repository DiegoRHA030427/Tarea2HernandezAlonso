package com.escom.android_views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.escom.android_views.R

// Placeholder de la sección "Formaciones". El contenido real se agrega en la Fase 2.
class FormacionesFragment : Fragment() {
    override fun onCreateView(
        inflador: LayoutInflater,
        contenedor: ViewGroup?,
        estadoGuardado: Bundle?
    ): View {
        return inflador.inflate(R.layout.fragment_formaciones, contenedor, false)
    }
}
