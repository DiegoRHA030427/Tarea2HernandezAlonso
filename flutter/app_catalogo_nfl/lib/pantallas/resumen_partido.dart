import 'package:flutter/material.dart';
import '../colores_nfl.dart';

// Sección 5: Resumen del Partido. Tarjeta tipo boleto, barra de progreso como
// reloj de juego, SnackBar para el tiempo fuera y AlertDialog para el reto de jugada.
class PantallaResumenPartido extends StatefulWidget {
  const PantallaResumenPartido({super.key});

  @override
  State<PantallaResumenPartido> createState() => _PantallaResumenPartidoState();
}

class _PantallaResumenPartidoState extends State<PantallaResumenPartido> {
  double _progresoReloj = 0.25;

  Future<void> _mostrarDialogoReto() async {
    final confirmado = await showDialog<bool>(
      context: context,
      builder: (context) => AlertDialog(
        title: const Text('Bandera de Reto'),
        content: const Text('¿El entrenador confirma el reto de la última jugada?'),
        actions: [
          TextButton(
            onPressed: () => Navigator.of(context).pop(false),
            child: const Text('Cancelar'),
          ),
          TextButton(
            onPressed: () => Navigator.of(context).pop(true),
            child: const Text('Confirmar'),
          ),
        ],
      ),
    );

    if (!mounted) return;
    final mensaje = confirmado == true
        ? '🚩 Reto confirmado: se revisa la jugada'
        : 'Reto cancelado por el entrenador';
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
            'Resumen del Partido',
            style: TextStyle(fontSize: 22, fontWeight: FontWeight.bold),
            textAlign: TextAlign.center,
          ),
          const SizedBox(height: 16),
          Container(
            padding: const EdgeInsets.all(20),
            decoration: BoxDecoration(
              color: azulMarinoEquipo,
              borderRadius: BorderRadius.circular(16),
              border: Border.all(color: doradoEquipo, width: 2),
            ),
            child: const Column(
              children: [
                Text('BOLETO OFICIAL DE PARTIDO', style: TextStyle(color: doradoEquipo)),
                SizedBox(height: 12),
                Text('Halcones Dorados vs Lobos Azules', style: TextStyle(color: blancoLineas, fontSize: 18)),
                SizedBox(height: 8),
                Text(
                  'Marcador: 21 - 17',
                  style: TextStyle(color: blancoLineas, fontSize: 24, fontWeight: FontWeight.bold),
                ),
              ],
            ),
          ),
          const SizedBox(height: 20),
          const Text('Reloj de juego', style: TextStyle(fontWeight: FontWeight.bold, color: verdeCampo)),
          const SizedBox(height: 8),
          LinearProgressIndicator(value: _progresoReloj, minHeight: 8),
          const SizedBox(height: 16),
          ElevatedButton(
            style: ElevatedButton.styleFrom(backgroundColor: verdeCampo),
            onPressed: () => setState(() {
              _progresoReloj = (_progresoReloj + 0.15).clamp(0.0, 1.0);
            }),
            child: const Text('Avanzar Reloj'),
          ),
          const SizedBox(height: 12),
          OutlinedButton(
            onPressed: () => ScaffoldMessenger.of(context).showSnackBar(
              const SnackBar(content: Text('⏱️ Tiempo fuera solicitado por el equipo')),
            ),
            child: const Text('Tiempo Fuera'),
          ),
          const SizedBox(height: 20),
          ElevatedButton(
            style: ElevatedButton.styleFrom(backgroundColor: amarilloPenalti, foregroundColor: negroArbitro),
            onPressed: _mostrarDialogoReto,
            child: const Text('Retar Jugada (Challenge)'),
          ),
        ],
      ),
    );
  }
}
