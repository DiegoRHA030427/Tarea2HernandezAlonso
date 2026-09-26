import 'package:flutter/material.dart';

// Pestaña "Más": agrupa el acceso a "Resumen del Partido" y "Formaciones",
// ya que el menú inferior solo admite 5 elementos.
class PantallaMas extends StatelessWidget {
  final VoidCallback irAResumen;
  final VoidCallback irAFormaciones;

  const PantallaMas({super.key, required this.irAResumen, required this.irAFormaciones});

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Padding(
        padding: const EdgeInsets.all(24),
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            SizedBox(
              width: double.infinity,
              child: ElevatedButton(
                onPressed: irAResumen,
                child: const Text('Resumen del Partido'),
              ),
            ),
            const SizedBox(height: 16),
            SizedBox(
              width: double.infinity,
              child: ElevatedButton(
                onPressed: irAFormaciones,
                child: const Text('Formaciones'),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
