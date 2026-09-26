import 'package:flutter/material.dart';
import '../colores_nfl.dart';

// Sección 2: Marcador y Acciones. Varios tipos de botones (relleno, contorno,
// texto y flotante) que simulan las anotaciones y decisiones de una pizarra.
class PantallaMarcadorAcciones extends StatelessWidget {
  const PantallaMarcadorAcciones({super.key});

  void _mostrarMensaje(BuildContext context, String mensaje) {
    ScaffoldMessenger.of(context).showSnackBar(SnackBar(content: Text(mensaje)));
  }

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      padding: const EdgeInsets.all(16),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          const Text(
            'Marcador y Acciones',
            style: TextStyle(fontSize: 22, fontWeight: FontWeight.bold),
            textAlign: TextAlign.center,
          ),
          const SizedBox(height: 16),
          ElevatedButton(
            style: ElevatedButton.styleFrom(
              backgroundColor: verdeCampo,
              padding: const EdgeInsets.symmetric(vertical: 14),
            ),
            onPressed: () => _mostrarMensaje(context, '¡TOUCHDOWN! Se anotan 6 puntos'),
            child: const Text('Touchdown (+6)'),
          ),
          const SizedBox(height: 12),
          OutlinedButton(
            style: OutlinedButton.styleFrom(padding: const EdgeInsets.symmetric(vertical: 14)),
            onPressed: () => _mostrarMensaje(context, 'Field Goal bueno: +3 puntos'),
            child: const Text('Field Goal (+3)'),
          ),
          const SizedBox(height: 12),
          TextButton(
            onPressed: () => _mostrarMensaje(context, 'Punto extra anotado: +1 punto'),
            child: const Text('Punto Extra (+1)'),
          ),
          const SizedBox(height: 24),
          Center(
            child: FloatingActionButton(
              backgroundColor: amarilloPenalti,
              foregroundColor: negroArbitro,
              onPressed: () => _mostrarMensaje(context, '🟨 Silbato del árbitro: jugada detenida'),
              child: const Icon(Icons.campaign),
            ),
          ),
        ],
      ),
    );
  }
}
