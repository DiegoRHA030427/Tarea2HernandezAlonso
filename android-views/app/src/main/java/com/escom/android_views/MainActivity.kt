package com.escom.android_views

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.escom.android_views.fragments.AjustesJuegoFragment
import com.escom.android_views.fragments.DraftContratosFragment
import com.escom.android_views.fragments.MarcadorAccionesFragment
import com.escom.android_views.fragments.MasFragment
import com.escom.android_views.fragments.RosterEquipoFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val navegacionInferior = findViewById<BottomNavigationView>(R.id.navegacion_inferior)

        // Mostrar la primera sección solo cuando la actividad se crea por primera vez;
        // si hay savedInstanceState, el FragmentManager ya restaura el fragmento visible.
        if (savedInstanceState == null) {
            mostrarFragmento(DraftContratosFragment())
        }

        navegacionInferior.setOnItemSelectedListener { item ->
            val fragmentoSeleccionado: Fragment = when (item.itemId) {
                R.id.menu_draft_contratos -> DraftContratosFragment()
                R.id.menu_marcador_acciones -> MarcadorAccionesFragment()
                R.id.menu_ajustes_juego -> AjustesJuegoFragment()
                R.id.menu_roster_equipo -> RosterEquipoFragment()
                R.id.menu_mas -> MasFragment()
                else -> return@setOnItemSelectedListener false
            }
            mostrarFragmento(fragmentoSeleccionado)
            true
        }
    }

    // Reemplaza el fragmento visible dentro del contenedor.
    // También la usa MasFragment para abrir "Resumen del Partido" y "Formaciones"
    // sin tener que agregar más pestañas al menú inferior.
    fun mostrarFragmento(fragmento: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.contenedor_fragmentos, fragmento)
            .commit()
    }
}
