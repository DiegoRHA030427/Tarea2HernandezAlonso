import 'package:flutter/material.dart';
import '../colores_nfl.dart';
import '../datos_globales.dart';

// Sección 4: Roster del Equipo. Lista vertical con al menos 15 posiciones fijas
// más los jugadores que se hayan reclutado en la Sección 1 (lista global).
class PantallaRosterEquipo extends StatelessWidget {
  const PantallaRosterEquipo({super.key});

  static const List<String> _rosterBase = [
    'QB - Mariscal de Campo',
    'RB - Corredor',
    'WR1 - Receptor Abierto',
    'WR2 - Receptor Abierto',
    'TE - Ala Cerrada',
    'LT - Tackle Izquierdo',
    'LG - Guardia Izquierdo',
    'C - Centro',
    'RG - Guardia Derecho',
    'RT - Tackle Derecho',
    'DE - Ala Defensiva',
    'DT - Tackle Defensivo',
    'LB - Apoyador',
    'CB - Esquinero',
    'S - Profundo',
  ];

  @override
  Widget build(BuildContext context) {
    final listaCompleta = [
      ..._rosterBase,
      ...jugadoresDraft.map((jugador) => '$jugador (Reclutado en Draft)'),
    ];

    return Column(
      children: [
        const Padding(
          padding: EdgeInsets.all(16),
          child: Text(
            'Roster del Equipo',
            style: TextStyle(fontSize: 22, fontWeight: FontWeight.bold),
          ),
        ),
        Expanded(
          child: ListView.builder(
            itemCount: listaCompleta.length,
            itemBuilder: (context, indice) {
              final esReclutado = indice >= _rosterBase.length;
              return Container(
                color: esReclutado ? doradoEquipo : Colors.transparent,
                padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 12),
                child: Text(listaCompleta[indice], style: const TextStyle(color: negroArbitro)),
              );
            },
          ),
        ),
      ],
    );
  }
}
