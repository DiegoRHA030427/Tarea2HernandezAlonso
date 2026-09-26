import 'package:flutter/material.dart';
import '../colores_nfl.dart';

// Sección 6: Formaciones. Row, Column y Box (Container) simulando posiciones en
// el campo, con AppBar propia ("NFL UI Catalog") y scroll general.
class PantallaFormaciones extends StatelessWidget {
  const PantallaFormaciones({super.key});

  Widget _casilla(String texto, Color color, {Color textColor = negroArbitro, double tamano = 48}) {
    return Container(
      width: tamano,
      height: tamano,
      alignment: Alignment.center,
      color: color,
      child: Text(texto, style: TextStyle(color: textColor, fontWeight: FontWeight.bold)),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('NFL UI Catalog'),
        backgroundColor: verdeCampo,
        foregroundColor: blancoLineas,
      ),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(16),
        child: Container(
          color: verdeCampo,
          padding: const EdgeInsets.all(16),
          child: Column(
            children: [
              const Text(
                'Formaciones',
                style: TextStyle(fontSize: 22, fontWeight: FontWeight.bold, color: blancoLineas),
              ),
              const SizedBox(height: 4),
              const Text(
                'Formación ofensiva: I-Formation',
                style: TextStyle(color: blancoLineas),
              ),
              const SizedBox(height: 20),

              // Columna que representa el campo completo.
              Column(
                children: [
                  // Fila de receptores.
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      _casilla('WR', doradoEquipo),
                      SizedBox(width: 48, height: 48),
                      _casilla('TE', doradoEquipo),
                    ],
                  ),
                  const SizedBox(height: 24),

                  // Línea ofensiva.
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: ['LT', 'LG', 'C', 'RG', 'RT']
                        .map(
                          (posicion) => Padding(
                            padding: const EdgeInsets.symmetric(horizontal: 4),
                            child: _casilla(posicion, azulMarinoEquipo, textColor: blancoLineas, tamano: 40),
                          ),
                        )
                        .toList(),
                  ),
                  const SizedBox(height: 24),

                  // Mariscal de campo.
                  _casilla('QB', rojoEquipo, textColor: blancoLineas),
                  const SizedBox(height: 24),

                  // Corredor.
                  _casilla('RB', rojoEquipo, textColor: blancoLineas),
                ],
              ),
            ],
          ),
        ),
      ),
    );
  }
}
